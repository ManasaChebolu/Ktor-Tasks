package com.panDetails

import com.panDetails.configuration.Configuration
import com.panDetails.databaseFactory.DatabaseFactory
import com.panDetails.plugins.callId
import com.panDetails.plugins.contentNegotiation
import com.panDetails.plugins.requestValidation
import com.panDetails.plugins.statusPage
import com.panDetails.routes.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args:Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    contentNegotiation()
    Configuration.init(environment)
    DatabaseFactory.init()
    callId()
    statusPage()
    requestValidation()
    configureRouting()
}

