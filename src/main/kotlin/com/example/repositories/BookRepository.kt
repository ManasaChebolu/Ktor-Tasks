package com.example.repositories

import com.example.data.authorsData
import com.example.data.categoryData
import com.example.databaseConnection.DatabaseFactory.dbQuery
import com.example.databaseConnection.tables.BookTable
import com.example.interfaces.BookInterface
import com.example.models.Book
import com.example.models.BooksList
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.*

class BookRepository : BookInterface {
    override suspend fun addBook(book: Book): Unit = dbQuery {
        BookTable.insert {
            it[title] = book.title!!
            it[author_id] = UUID.fromString(book.authorId!!)
            it[category_id] = UUID.fromString(book.categoryId!!)
            it[publication_date] = book.publicationDate!!
            it[price] = book.price
            it[description] = book.description.toString()
        }
    }

    override suspend fun updateBook(id: UUID, price: Double): Int = dbQuery {
        BookTable.update({ BookTable.id eq id }) {
            it[this.price] = price
        }
    }

    override suspend fun deleteBook(id: UUID): Int = dbQuery {
        BookTable.deleteWhere { BookTable.id eq id }
    }

    override suspend fun getAllBooks():List<BooksList> = dbQuery{
            BookTable.selectAll()
                .map { BooksList(
                        id = it[BookTable.id].value.toString(),
                        title = it[BookTable.title],
                        author = authorsData(it[BookTable.author_id].value),
                        category = categoryData(it[BookTable.category_id].value),
                        publicationDate = it[BookTable.publication_date],
                        price = it[BookTable.price],
                        description = it[BookTable.description]
                    )
                }
    }
}