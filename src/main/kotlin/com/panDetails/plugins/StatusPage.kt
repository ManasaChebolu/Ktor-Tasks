package com.panDetails.plugins

import com.panDetails.models.NegativeResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import java.lang.RuntimeException

fun Application.statusPage() {
    install(StatusPages) {
        exception<Throwable> {call , cause ->
            when(cause) {
                is PanNumberNotExistException -> call.respond(HttpStatusCode.NotFound,cause.msg)
                is TableNotExistException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                is NotNullOrBlankException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                is OCRFailedException -> call.respond(HttpStatusCode.BadRequest,cause.msg)
                else -> call.respond(HttpStatusCode.InternalServerError,cause)
            }
        }
    }
}

class PanNumberNotExistException(val msg: NegativeResponse) : RuntimeException()
class TableNotExistException(val msg: NegativeResponse) : RuntimeException()
class NotNullOrBlankException(val msg: NegativeResponse) : RuntimeException()
class OCRFailedException(val msg:NegativeResponse) : RuntimeException()


