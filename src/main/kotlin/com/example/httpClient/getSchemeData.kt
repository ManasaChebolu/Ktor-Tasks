package com.example.httpClient

import com.example.models.UrlData
import com.google.gson.Gson
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun getSchemeData(schemeCode : Int) :UrlData  {
        val schemeData = getClient().get("https://api.mfapi.in/mf/$schemeCode").bodyAsText()
        return Gson().fromJson(schemeData, UrlData::class.java)
}
