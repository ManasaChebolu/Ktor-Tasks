package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.contentNegotiation
import com.example.plugins.statusPage
import com.example.plugins.requestValidation
import com.example.plugins.configureRouting
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
