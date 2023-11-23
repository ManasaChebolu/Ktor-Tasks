package com.panDetails.routes

import com.panDetails.models.Request
import com.panDetails.services.PanServices
import com.panDetails.utlis.ApiEndPoints
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.header
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.post

fun Application.configureRouting() {
    routing {
        val panService = PanServices()

        post(ApiEndPoints.OCR_VERIFICATION) {
            val request = call.receive<Request>()
            val callId = call.request.header(HttpHeaders.XRequestId)
            println("Call ID: $callId")
            call.respond(HttpStatusCode.OK, panService.panRepoResult(request.panNumber!!))
        }
    }
}

