package com.example.serviceTesting

import com.example.databaseConnection.DatabaseFactory
import com.example.models.Book
import com.example.models.ResponseMessage
import com.example.services.BookServices
import com.example.statusPage.IDNotFoundException
import com.example.statusPage.NotNullOrBlankException
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ServiceTesting {
    private val bookService=BookServices()
    @Before
    fun setUp() {
        DatabaseFactory.init()
    }
    @Test
    fun testAServiceAddBook(): Unit = runBlocking {
        var  request  = Book(title = "A Brief History of Humankind",
            authorId = "dd2ca556-cf9d-4565-a969-e79e43f1d943" ,
            categoryId = "3e8d9301-552e-4278-9519-2fd0acf9f229",
            publicationDate = "1767-2-9",
            price= 67.54)
        bookService.addBookService(request).apply {
            assertEquals(ResponseMessage("Book Added Successfully ", true),this)
        }
         request = Book(title = "kill",
        authorId = "dd2ca556-cf9d-4565-a969-e79e43f1d97" ,
        categoryId = "3e8d9301-552e-4278-9519-2fd0acf9f229",
        publicationDate = "1767-2-9",
        price= 0.0)
        assertFailsWith<IDNotFoundException> {
            bookService.addBookService(request).apply {
                assertEquals(ResponseMessage("No such authorId or categoryId", false),this)
            }
        }
    }

    @Test
    fun testBServiceUpdateBook():Unit = runBlocking {
        bookService.updateBookService("76d8606d-1d5a-48c7-82d5-e628009b114a", 87.98).apply {
            assertEquals(ResponseMessage("Book Updated Successfully with id: 76d8606d-1d5a-48c7-82d5-e628009b114a", true),this)
        }
        assertFailsWith<NotNullOrBlankException> {
            bookService.updateBookService(null,0.0).apply {
                assertEquals(ResponseMessage("ID shouldn't be null Or blank",false),this)
            }
        }
    }
    @Test
    fun testCServiceDeleteBook():Unit = runBlocking {
        bookService.deleteBookService("76d8606d-1d5a-48c7-82d5-e628009b114a").apply {
            assertEquals(ResponseMessage("Book deleted Successfully with id : 76d8606d-1d5a-48c7-82d5-e628009b114a", true),this)
        }
        assertFailsWith<IDNotFoundException> {
            bookService.deleteBookService("6fab746-89e3-4011-948e-ac6ca337ed6").apply {
                assertEquals(ResponseMessage("No such book with id: 6fab746-89e3-4011-948e-ac6ca337ed6", false),this)
            }
        }
    }
    @Test
    fun testDServiceGetAllBooks():Unit = runBlocking {
        bookService.getBooksService().apply {
            assertEquals("The Alchemist",this[0].title)
        }
    }
}