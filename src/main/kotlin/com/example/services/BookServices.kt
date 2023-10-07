package com.example.services

import com.example.data.authorsData
import com.example.data.categoryData
import com.example.models.Book
import com.example.models.BooksList
import com.example.models.ResponseMessage
import com.example.repositories.BookRepository
import com.example.statusPage.*
import java.util.UUID

class BookServices {
    private val bookRepository =BookRepository()

    suspend fun addBookService(book: Book) :ResponseMessage{
            val authorID   = book.authorId?.let { UUID.fromString(it) }
            val categoryID = book.categoryId?.let { UUID.fromString(it) }
            if (( authorID == null) || (categoryID == null))
                throw NotNullOrBlankException(ResponseMessage("ID shouldn't be null Or blank ", false))
            else {
                val author = authorsData(authorID)
                val category = categoryData(categoryID)
                if (author.isEmpty() || category.isEmpty())
                    throw IDNotFoundException(ResponseMessage("No such authorId or categoryId", false))
                else {
                    bookRepository.addBook(book)
                    return ResponseMessage("Book Added Successfully ", true)
                }
            }
    }

    suspend fun updateBookService(iD:String?,price:Double):ResponseMessage {
        val id = iD?.let { UUID.fromString(it) }
        return if(id == null)
            throw NotNullOrBlankException(ResponseMessage("ID shouldn't be null Or blank",false))
        else {
            val row=bookRepository.updateBook(id,price)
            if (row == 1)
                ResponseMessage("Book Updated Successfully with id: $id", true)
            else
                throw IDNotFoundException(ResponseMessage("No such book with id: $id", false))
        }
    }

    suspend fun deleteBookService(iD: String?) :ResponseMessage{
        val id = iD?.let { UUID.fromString(it)}
        return if(id == null)
            throw NotNullOrBlankException(ResponseMessage("ID shouldn't be null or blank",false))
        else {
            val row=bookRepository.deleteBook(id)
            if (row == 1)
                ResponseMessage("Book deleted Successfully with id : $id", true)
            else
                throw IDNotFoundException(ResponseMessage("No such book with id: $id", false))
        }
    }

    suspend fun getBooksService():List<BooksList> {
        val books=bookRepository.getAllBooks()
        if(books.isEmpty())
            throw NotNullOrBlankException(ResponseMessage( "No Books Added yet!!",false))
        else
            return books
    }

}