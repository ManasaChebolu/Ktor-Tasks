package com.example.plugins

import com.example.models.RequestBySchemeCode
import com.example.models.RequestBySchemeName
import com.example.services.SchemeService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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
            call.respond(HttpStatusCode.OK,schemeService.postMetaDataRepoResult(request.request.schemeId,request.request.filter!!))
        }

    }
}
