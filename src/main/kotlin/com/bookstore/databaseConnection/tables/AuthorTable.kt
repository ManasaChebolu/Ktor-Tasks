package com.bookstore.databaseConnection.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object AuthorTable:UUIDTable("author") {
    val author_name=varchar("name",150)
}