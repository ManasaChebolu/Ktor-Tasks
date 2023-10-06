package com.example.serviceTesting

import com.example.databaseConnection.tables.AuthorTable
import com.example.databaseConnection.tables.BookTable
import com.example.databaseConnection.tables.CategoryTable
import com.example.main
import com.example.models.Book
import com.example.models.ResponseMessage
import com.example.services.BookServices
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertEquals

class ServiceTesting {
    private val bookService=BookServices()
    private val request = Book(title = "The Hitchhiker's Guide to the Galaxy",
        authorId = "ba60bb29-495e-450e-a0f7-db71a40311f1",
        categoryId = "0cdc3f3e-d8d4-488c-b5bf-d64f51c7c558",
        publicationDate = "1764-2-12",
        price=12.54,
        description = "A humorous science fiction series about the misadventures of an unwitting human.")

    @Test
    fun testServiceAddBook(): Unit = runBlocking {
        bookService.addBookService(request).apply {
            assertEquals(ResponseMessage("Book Added Successfully ", true),this)
        }
    }


}