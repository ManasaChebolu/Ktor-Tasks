package com.example.plugins

import com.example.models.ResponseMessage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import java.lang.RuntimeException


fun Application.statusPage() {
    install(StatusPages) {
        exception<Exception> { call, cause ->
            when(cause) {
                is TableNotExistsException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                is SchemeNameNotExistException -> call.respond(HttpStatusCode.NotFound,cause.msg)
                is NotNullOrBlankException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                is IDNotExistException -> call.respond(HttpStatusCode.NotFound,cause.msg)
                is InvalidIdException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                is InvalidFilterException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                else -> call.respond(HttpStatusCode.InternalServerError,cause)
            }
        }
    }
}

class SchemeNameNotExistException(val msg :ResponseMessage) :RuntimeException()
class TableNotExistsException(val msg: ResponseMessage) : RuntimeException()
class NotNullOrBlankException(val msg: ResponseMessage) : RuntimeException()
class IDNotExistException(val msg: ResponseMessage) : RuntimeException()
class InvalidIdException(val msg: ResponseMessage) : RuntimeException()
class InvalidFilterException(val msg: ResponseMessage) :RuntimeException()