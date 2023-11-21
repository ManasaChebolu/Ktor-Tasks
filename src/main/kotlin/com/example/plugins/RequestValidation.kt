package com.example.plugins

import com.example.models.RequestBySchemeCode
import com.example.models.RequestBySchemeName
import com.example.models.ResponseMessage
import com.example.utlis.InfoMessage
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.requestValidation() {
    install(RequestValidation) {

        validate<RequestBySchemeName> {
            when {
                it.request.schemeName.isNullOrBlank() -> throw NotNullOrBlankException(ResponseMessage(InfoMessage.INVALID_NULL_OR_BLANK,false))
                else -> ValidationResult.Valid
            }
        }

        validate<RequestBySchemeCode> {
            when {
                it.request.schemeId <= 0 -> throw InvalidIdException(ResponseMessage(InfoMessage.INVALID_ID,false))
                ((it.request.filter != "1M") &&
                        (it.request.filter !="1W") &&
                        (it.request.filter !="1Y") &&
                        (it.request.filter !="5Y")) -> throw InvalidFilterException(ResponseMessage(InfoMessage.INVALID_FILTER,false))

                else -> ValidationResult.Valid
            }
        }
    }
}