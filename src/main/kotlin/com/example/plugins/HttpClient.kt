package com.example.plugins

import com.example.models.ProductApi
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import  io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import com.google.gson.*

suspend fun getData(): ProductApi {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            Gson()
        }
    }
    val response = client.get("https://dummyjson.com/products")
    return Gson().fromJson(response.bodyAsText(), ProductApi::class.java)
}
