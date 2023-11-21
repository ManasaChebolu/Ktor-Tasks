package com.example.httpClient

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*

 fun getClient(): HttpClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    return client
}


