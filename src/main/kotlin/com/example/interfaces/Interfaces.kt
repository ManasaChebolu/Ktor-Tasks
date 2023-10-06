package com.example.interfaces

import com.example.models.Book
import com.example.models.BooksList
import java.util.UUID

interface BookInterface {
    suspend fun addBook( book:Book)
    suspend fun updateBook(id:UUID,price:Double):Int
    suspend fun deleteBook(id:UUID):Int
    suspend fun getAllBooks():List<BooksList>
}