package com.example.data

import com.example.databaseConnection.tables.AuthorTable
import com.example.models.Author
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

 fun authorsDataInsert() = transaction {
    AuthorTable.batchInsert(
        listOf( Author(authorName = "J.K. Rowling"),
            Author(authorName = "George R.R. Martin"),
            Author(authorName = "Agatha Christie"),
            Author(authorName = "Stephen King"),
            Author(authorName = "Jane Austen")) ){
            this[AuthorTable.author_name]=it.authorName
    }
}
fun authorsData(id:UUID) :List<Author>  {
    val authorList=
        transaction {
            AuthorTable.select(AuthorTable.id eq id)
                .map {
                    Author(
                        it[AuthorTable.id].value.toString(),
                        it[AuthorTable.author_name]
                    )
                }
        }
    return authorList
}