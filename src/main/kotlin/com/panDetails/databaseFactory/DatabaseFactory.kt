package com.panDetails.databaseFactory

import com.panDetails.configuration.Configuration
import com.panDetails.databaseFactory.tables.OcrInfoTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val url = Configuration.env.url
        val driver = Configuration.env.driver
        val userName = Configuration.env.user
        val password = Configuration.env.password
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

