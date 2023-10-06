package com.example.data

import com.example.databaseConnection.tables.CategoryTable
import com.example.models.Category
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID


 fun categoryDataInsert() = transaction {
    CategoryTable.batchInsert(listOf(Category(categoryName = "Health/Fitness"),
        Category(categoryName ="Fiction"),
        Category(categoryName = "History"),
        Category(categoryName = "Travel"),
        Category(categoryName = "Stories"),
        Category(categoryName ="Software" ))) {
        this[CategoryTable.category_name]=it.categoryName
    }
}

 fun categoryData(id:UUID):List<Category> {
    val categoryList= transaction {
        CategoryTable.select(CategoryTable.id eq id)
            .map {
                Category(
                    it[CategoryTable.id].value.toString(),
                    it[CategoryTable.category_name]
                )
            }
    }
     return categoryList
 }