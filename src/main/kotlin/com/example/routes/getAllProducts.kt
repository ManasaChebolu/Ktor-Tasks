package com.example.routes

import com.example.databaseConnection.ProductsEntity
import com.example.models.ResponseMessage
import com.example.plugins.getData
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.http.HttpStatusCode

//get all products
fun Route.getAllProducts() {
    get("/getAllProducts") {
        try {
            val productsData = getData()
            transaction {
                for (product in productsData.products) {
                    ProductsEntity.insert {
                        it[id] = product.id
                        it[title] = product.title
                        it[description] = product.description
                        it[price] = product.price
                        it[discountPercentage] = product.discountPercentage
                        it[rating] = product.rating
                        it[stock] = product.stock
                        it[brand] = product.brand
                        it[category] = product.category
                        it[thumbnail] = product.thumbnail
                        it[images] = product.images.toString()
                    }
                }
            }
            call.respond(HttpStatusCode.OK,ResponseMessage("Inserted Successfully", true))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError,ResponseMessage(e.toString(), false))
        }
    }
}