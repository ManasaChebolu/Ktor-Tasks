package com.example.utlis.helperFunctions

import com.example.models.RequestBySchemeCode
import com.example.models.RequestBySchemeName
import com.example.models.ResponseMessage
import com.example.plugins.InvalidFilterException
import com.example.plugins.InvalidIdException
import com.example.plugins.NotNullOrBlankException
import com.example.utlis.InfoMessage

class HelperFunctions  {
    fun validateSchemeName(request: RequestBySchemeName) {
        if (request.request.schemeName.isNullOrBlank()) {
            throw NotNullOrBlankException(ResponseMessage(InfoMessage.INVALID_NULL_OR_BLANK, false))
        }
    }

    fun validateSchemeCode(request: RequestBySchemeCode) {
        if (request.request.schemeId <= 0) {
            throw InvalidIdException(ResponseMessage(InfoMessage.INVALID_ID, false))
        }

        if (!listOf("1M", "1W", "1Y", "5Y").contains(request.request.filter)) {
            throw InvalidFilterException(ResponseMessage(InfoMessage.INVALID_FILTER, false))
        }
    }

}

