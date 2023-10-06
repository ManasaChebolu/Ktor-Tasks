package com.example.databaseConnection

import com.example.databaseConnection.tables.AuthorTable
import com.example.databaseConnection.tables.BookTable
import com.example.databaseConnection.tables.CategoryTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val url = "jdbc:postgresql://localhost:8080/BookStore"
        val driver = "org.postgresql.Driver"
        val userName = "postgres"
        val password = "Chebolu@03"
        Database.connect(url, driver, userName, password)
        transaction {
            SchemaUtils.create(AuthorTable,CategoryTable,BookTable)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction {
                block()
            }
        }
}