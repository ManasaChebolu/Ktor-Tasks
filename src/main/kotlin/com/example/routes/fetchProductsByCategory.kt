package com.example.routes

import com.example.databaseConnection.ProductsEntity
import com.example.models.Products
import com.example.models.ResponseMessage
import com.example.models.ProductApi
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.fetchProductsByCategory() {
    post("/fetchProductsByCategory") {
        try {
            val userRequest=call.receive<Map<String,String>>()
            val query=userRequest["query"] ?:return@post call.respond("Provide required fields")
            val row=transaction{
                ProductsEntity.select{ ProductsEntity.category eq query}
                    .map { Products(it[ProductsEntity.id] ,
                        it[ProductsEntity.title],
                        it[ProductsEntity.description],
                        it[ProductsEntity.price],
                        it[ProductsEntity.discountPercentage],
                        it[ProductsEntity.rating],
                        it[ProductsEntity.stock],
                        it[ProductsEntity.brand],
                        it[ProductsEntity.category],
                        it[ProductsEntity.thumbnail],
                        mutableListOf(it[ProductsEntity.images]))}
            }
            call.respond(HttpStatusCode.OK, ProductApi(row))
        }catch(e:Exception) {
            call.respond(HttpStatusCode.InternalServerError, ResponseMessage(e.toString(), false))
        }
    }
}