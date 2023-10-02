package com.example.database

import org.jetbrains.exposed.sql.Table

object ProductsEntity: Table("productlist") {
    val id=integer("id")
    var title=varchar("title",1500)
    val description =varchar("description",1500)
    val price=integer("price")
    val discountPercentage=double("discountPercentage")
    val rating=double("rating")
    val stock=integer("stock")
    val brand=varchar("brand",1500)
    val category=varchar("category",1500)
    val thumbnail =varchar("thumbnail",1500)
    val images = text("images").default("[]")

    override val primaryKey =PrimaryKey(id)
}