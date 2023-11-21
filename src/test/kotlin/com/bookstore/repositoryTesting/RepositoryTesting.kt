package com.bookstore.repositoryTesting

import com.bookstore.databaseConnection.DatabaseFactory
import com.bookstore.models.Book
import com.bookstore.repositories.BookRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RepositoryTesting {
    private val bookRepo = BookRepository()
    @Before
    fun setUp() {
        DatabaseFactory.init()
    }
    @Test
    fun testARepoAddBook()= runBlocking {
        val  request  = Book(title = "A Brief History of Humankind",
            authorId = "dd2ca556-cf9d-4565-a969-e79e43f1d943" ,
            categoryId = "3e8d9301-552e-4278-9519-2fd0acf9f229",
            publicationDate = "1767-2-9",
            price= 67.54)
        bookRepo.addBook(request).apply {
            assertTrue(this.toString().isNotEmpty())
        }
    }
    @Test
    fun testBRepoUpdateBook(): Unit = runBlocking {
        bookRepo.updateBook(id= UUID.fromString( "92dd96b2-71d6-40fe-bf2b-f173f464bd32"),43.56).apply {
            assertEquals(1,this)
        }
        bookRepo.updateBook(id=UUID.fromString("dd5b2855-6317-4d58-9ca4-2fd0acf9f229"),342.5).apply {
            assertEquals(0,this)
        }
    }
    @Test
    fun testCRepoDeleteBook(): Unit = runBlocking {
        bookRepo.deleteBook(id= UUID.fromString( "92dd96b2-71d6-40fe-bf2b-f173f464bd32")).apply {
            assertEquals(1,this)
        }
        bookRepo.deleteBook(id=UUID.fromString("dd5b2855-6317-4d58-9ca4-2fd0acf9f229")).apply {
            assertEquals(0,this)
        }
    }
    @Test
    fun testDRepoGetAllBooks(): Unit = runBlocking {
        bookRepo.getAllBooks().apply {
            assertEquals("The Alchemist",this[0].title)
        }
    }


}