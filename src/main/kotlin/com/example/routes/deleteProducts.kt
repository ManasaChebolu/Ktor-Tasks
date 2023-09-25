package com.example.routes

import com.example.databaseConnection.ProductsEntity
import com.example.models.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.transaction

fun Route.deleteProductsByCategory() {
    post("/deleteProducts") {
        try {
            val userRequest=call.receive<Map<String,String>>()
            val query=userRequest["query"] ?:return@post call.respond("Provide required fields")
            val row=transaction{
                ProductsEntity.deleteWhere { category eq query }
            }
            if (row>=1)
                call.respond(HttpStatusCode.OK,ResponseMessage("Deleted successfully",true))
            else
                call.respond(HttpStatusCode.NotImplemented,ResponseMessage("Failed to delete data",false))
        }catch (e:Exception) {
            call.respond(HttpStatusCode.InternalServerError,ResponseMessage(e.toString(), false))
        }
    }
}