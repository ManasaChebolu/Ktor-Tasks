package com.bookstore.databaseConnection.tables

import org.jetbrains.exposed.dao.id.UUIDTable
object BookTable :UUIDTable("book") {
    val title= varchar("title",150)
    val author_id=reference("author_id",AuthorTable)
    val category_id=reference("category_id",CategoryTable)
    val publication_date=varchar("publication_date",150)
    val price=double("price")
    val description=varchar("Description",1500)
}