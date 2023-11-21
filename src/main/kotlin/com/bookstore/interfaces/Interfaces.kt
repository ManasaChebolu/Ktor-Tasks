package com.bookstore.interfaces

import com.bookstore.models.Book
import com.bookstore.models.BooksList
import java.util.UUID

interface BookInterface {
    suspend fun addBook( book:Book)
    suspend fun updateBook(id:UUID,price:Double):Int
    suspend fun deleteBook(id:UUID):Int
    suspend fun getAllBooks():List<BooksList>
}