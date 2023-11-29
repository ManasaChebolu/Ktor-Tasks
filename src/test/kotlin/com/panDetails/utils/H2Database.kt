package com.panDetails.utils

import com.panDetails.databaseFactory.tables.OcrInfoTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object H2Database {
    private var database : Database? = null
    fun init() {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=create domain if not exists jsonb as other;"
        config.maximumPoolSize = 2
        config.isAutoCommit = true
        config.username = "postgres"
        config.password = "root"
        config.validate()
        val source = HikariDataSource(config)
        database = Database.connect(source)
        transaction(database) {
            SchemaUtils.create(OcrInfoTable)
        }
    }
    fun dropTestDb() {
        transaction(database) {
            SchemaUtils.drop(OcrInfoTable)
        }
    }
}