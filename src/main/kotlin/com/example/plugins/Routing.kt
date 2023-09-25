package com.example.plugins

import com.example.databaseConnection.DatabaseFactory
import com.example.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    DatabaseFactory.init()
    routing {
        getAllProducts()
        searchProducts()
        fetchProductsByCategory()
        deleteProductsByCategory()
        updateProductById()
    }
}
