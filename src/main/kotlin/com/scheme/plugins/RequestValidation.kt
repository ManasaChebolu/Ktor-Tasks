package com.scheme.plugins

import com.scheme.models.RequestBySchemeCode
import com.scheme.models.RequestBySchemeName
import com.scheme.utlis.helperFunctions.HelperFunctions
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


