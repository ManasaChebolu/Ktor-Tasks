package com.example.databaseConnection

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
                val url = "jdbc:postgresql://localhost:8080/Products"
                val driver = "org.postgresql.Driver"
                val userName = "postgres"
                val password = "Chebolu@03"
                Database.connect(url, driver, userName, password)
                transaction {
                    SchemaUtils.create(ProductsEntity)
                }
    }
}