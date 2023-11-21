package com.example

import com.example.database.DatabaseFactory
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

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
