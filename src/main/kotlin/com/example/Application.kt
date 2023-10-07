package com.example

import com.example.data.tableData
import com.example.databaseConnection.DatabaseFactory
import com.example.plugins.*
import com.example.statusPage.statusPage
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

 fun Application.module() {
    DatabaseFactory.init()
     tableData()
     contentNegotiation()
     statusPage()
    configureRouting()
}
