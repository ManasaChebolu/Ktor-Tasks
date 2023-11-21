package com.scheme.routes

import com.scheme.models.RequestBySchemeCode
import com.scheme.models.RequestBySchemeName
import com.scheme.services.SchemeService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post


fun Application.configureRouting() {
    val schemeService = SchemeService()
    routing {

        get("/schemeData") {
            call.respond(HttpStatusCode.OK,schemeService.getSchemeRepoResult())
        }

        post("/searchDataBySchemeName") {
            val request = call.receive<RequestBySchemeName>()
            call.respond(HttpStatusCode.OK,schemeService.postSchemeRepoResult(request.request.schemeName!!))
        }

        post("/searchDataBySchemeCode") {
            val request = call.receive<RequestBySchemeCode>()
            call.respond(HttpStatusCode.OK,
                schemeService.postMetaDataRepoResult(request.request.schemeId,request.request.filter!!))
        }

    }
}
