package com.example

import com.example.data.tableData
import com.example.databaseConnection.tables.AuthorTable
import com.example.databaseConnection.tables.BookTable
import com.example.databaseConnection.tables.CategoryTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection

fun main() {
    val db= Database.connect(
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
        "org.h2.Driver"
    )
    @Before
    fun setUp() {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(db) {
            SchemaUtils.create(BookTable, AuthorTable, CategoryTable)
            tableData()
        }
    }
    @After
    fun drop() {
        transaction(db) {
            SchemaUtils.drop(BookTable, AuthorTable, CategoryTable)
        }
    }
}
