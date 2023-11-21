package com.example.plugins

import com.example.models.RequestBySchemeCode
import com.example.models.RequestBySchemeName
import com.example.utlis.helperFunctions.HelperFunctions
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.application.Application

fun Application.requestValidation() {
    val helperFunctions = HelperFunctions()
    install(RequestValidation) {
        validate<RequestBySchemeName> {
            helperFunctions.validateSchemeName(it)
            ValidationResult.Valid
        }

        validate<RequestBySchemeCode> {
            helperFunctions.validateSchemeCode(it)
            ValidationResult.Valid
        }
    }
}


