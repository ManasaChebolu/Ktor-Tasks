package com.example

import org.jetbrains.exposed.sql.Database
object H2Databse  {
    fun init() : Database {
        return Database.connect(
             "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            "org.h2.Driver")
    }
}