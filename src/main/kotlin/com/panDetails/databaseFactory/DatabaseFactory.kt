package com.panDetails.databaseFactory

import com.panDetails.databaseFactory.tables.OcrInfoTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val url = "jdbc:postgresql://localhost:5432/pandetails"
        val driver = "org.postgresql.Driver"
        val userName = "postgres"
        val password = "Chebolu@03"
        Database.connect(url, driver, userName, password)
        transaction {
            SchemaUtils.createMissingTablesAndColumns(OcrInfoTable)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction {
                block()
            }
        }
}

