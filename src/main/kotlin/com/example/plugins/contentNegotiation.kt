package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import kotlinx.serialization.json.Json
import io.ktor.server.plugins.contentnegotiation.*

fun Application.contentNegotiation() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint=true
            encodeDefaults=true
        })
    }
}