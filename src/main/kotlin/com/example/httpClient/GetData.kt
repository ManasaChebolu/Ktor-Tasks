package com.example.httpClient

import com.example.models.Scheme
import com.google.gson.Gson
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun getData() : Array<Scheme> {
    val schemeData = getClient().get("https://api.mfapi.in/mf")
    return Gson().fromJson(schemeData.bodyAsText(), Array<Scheme>::class.java)
}
