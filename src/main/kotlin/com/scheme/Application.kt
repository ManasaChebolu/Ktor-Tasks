package com.scheme

import com.scheme.database.DatabaseFactory
import com.scheme.plugins.contentNegotiation
import com.scheme.plugins.statusPage
import com.scheme.plugins.requestValidation
import com.scheme.routes.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain


fun main(args:Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    contentNegotiation()
    statusPage()
    DatabaseFactory.init()
    requestValidation()
    configureRouting()
}
