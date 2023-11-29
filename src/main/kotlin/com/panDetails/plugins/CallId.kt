package com.panDetails.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callid.*

fun Application.callId() {
    install(CallId) {
    }

}




