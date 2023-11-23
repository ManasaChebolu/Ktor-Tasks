package com.panDetails.plugins

import com.panDetails.models.NegativeResponse
import com.panDetails.models.Request
import com.panDetails.utlis.InfoMessage
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun Application.requestValidation() {
    install(RequestValidation) {
        validate<Request> {
            when {
            it.panNumber.isNullOrBlank() -> throw NotNullOrBlankException(
                                                        NegativeResponse(InfoMessage.INVALID_IN_FOLD,
                                                            InfoMessage.INVALID_NULL_OR_BLANK))
                else -> ValidationResult.Valid
            }
        }
    }
}

