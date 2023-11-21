package com.scheme.httpClient

import com.scheme.models.UrlData
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun getSchemeData(schemeCode : Int) :UrlData  {
        val schemeData = getClient().get("https://api.mfapi.in/mf/$schemeCode").bodyAsText()
        return Gson().fromJson(schemeData, UrlData::class.java)
}
