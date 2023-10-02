package com.example.plugins

import com.example.models.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.statusPage() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if(cause is ExposedSQLException)
                call.respond(HttpStatusCode.InternalServerError, ResponseMessage("Failed to insert data", false))
            else
                call.respond(HttpStatusCode.InternalServerError, ResponseMessage("provide required fields and it shouldn't be null", false))
        }
    }
}