package com.example.statusPage

import com.example.models.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import kotlinx.serialization.Serializable
import java.lang.Exception

fun Application.statusPage() {
    install(StatusPages) {
            exception<Throwable> { call,cause ->
                when (cause) {
                    is NotNullOrBlankException ->  call.respond(HttpStatusCode.BadRequest, cause.msg)
                    is IDNotFoundException -> call.respond(HttpStatusCode.NotFound,  cause.msg)
                    else -> call.respond(HttpStatusCode.BadRequest,ResponseMessage(cause.message.toString(),false))
                }
            }
    }
}

class NotNullOrBlankException(val msg:ResponseMessage):RuntimeException()
class IDNotFoundException(val msg: ResponseMessage):RuntimeException()
