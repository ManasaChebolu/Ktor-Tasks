package com.panDetails.routes

import com.panDetails.models.Request
import com.panDetails.services.PanServices
import com.panDetails.utlis.ApiEndPoints
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.routing
import io.ktor.server.routing.post

fun Application.configureRouting() {
    routing {
        val panService = PanServices()

        post(ApiEndPoints.OCR_VERIFICATION) {
            val requests = call.receive<Request>()
            val callId = call.request.header("Call-Id")
            println(callId)
            call.respond(HttpStatusCode.OK, panService.panRepoResult(requests.panNumber!!))
        }
    }
}

