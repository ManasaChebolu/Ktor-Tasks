package com.example.routes

import com.example.databaseConnection.ProductsEntity
import com.example.models.Products
import com.example.models.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun Route.updateProductById() {
    post("/updateProducts") {
        try {
            val userRequest =call.receive<Products>()
            val id=userRequest.id
            val title=userRequest.title
            val row= transaction {
                ProductsEntity.update({ProductsEntity.id eq id}){
                    it[ProductsEntity.title]=title
                }
            }
            if (row>=1)
                call.respond(HttpStatusCode.OK,ResponseMessage("Updated successfully",true))
            else
                call.respond(HttpStatusCode.NotImplemented,ResponseMessage("Failed to update data",false))
        }catch(e:Exception) {
            call.respond(HttpStatusCode.InternalServerError, ResponseMessage(e.toString(), false))
        }
    }
}