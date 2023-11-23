package com.panDetails

import org.jetbrains.exposed.sql.Database

object H2Database  {
    fun init() : Database {
        return Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            "org.h2.Driver")
    }
}


