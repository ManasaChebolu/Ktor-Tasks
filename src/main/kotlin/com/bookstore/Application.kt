package com.bookstore

import com.bookstore.data.tableData
import com.bookstore.databaseConnection.DatabaseFactory
import com.bookstore.plugins.*
import com.bookstore.statusPage.statusPage
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
