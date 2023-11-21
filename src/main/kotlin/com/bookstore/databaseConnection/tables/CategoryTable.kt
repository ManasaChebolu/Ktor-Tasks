package com.bookstore.databaseConnection.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object CategoryTable: UUIDTable("category") {
    val category_name=varchar("name",150)
}