package com.panDetails.httpClient

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

fun getClient(): HttpClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    return client
}

