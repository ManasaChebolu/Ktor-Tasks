package com.example.routes

import com.example.models.ProductApi
import com.example.models.Products
import com.example.models.Query
import com.example.models.ResponseMessage
import com.example.services.ProductServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val productService=ProductServices()
    routing {
            //get all products from url
            get("/getAllProducts") {
                    productService.handleGetAllProducts()
                    call.respond(HttpStatusCode.OK, ResponseMessage("Inserted Successfully", true))
            }
            //search products by title or description
            post("/searchProducts") {
                val userRequest = call.receive<Query>()
                val row = productService.handleSearchProducts(userRequest.query)
                call.respond(HttpStatusCode.OK, ProductApi(row))
            }
            //fetch products by category
            post("/fetchProductsByCategory") {
                    val userRequest = call.receive<Query>()
                    val row = productService.handleFetchProducts(userRequest.query)
                    call.respond(HttpStatusCode.OK, ProductApi(row))
            }
            //delete product by category
            post("/deleteProducts") {
                val userRequest = call.receive<Query>()
                val row = productService.handleDeleteProduct(userRequest.query)
                call.respond(HttpStatusCode.OK, row)
            }
            //update product by id
            post("/updateProducts") {
                val userRequest = call.receive<Products>()
                val row = productService.handleUpdateProduct(userRequest.id, userRequest.title)
                call.respond(HttpStatusCode.OK,row)
            }
    }

}

