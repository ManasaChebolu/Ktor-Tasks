package com.scheme.utlis.helperFunctions

import com.scheme.models.RequestBySchemeCode
import com.scheme.models.RequestBySchemeName
import com.scheme.models.ResponseMessage
import com.scheme.plugins.InvalidFilterException
import com.scheme.plugins.InvalidIdException
import com.scheme.plugins.NotNullOrBlankException
import com.scheme.utlis.InfoMessage

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

