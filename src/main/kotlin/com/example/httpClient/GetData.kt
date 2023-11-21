package com.example.httpClient

import com.example.models.Scheme
import com.google.gson.Gson
import io.ktor.client.request.*
import io.ktor.client.statement.*

suspend fun getData() : Array<Scheme> {
    val schemeData = getClient().get("https://api.mfapi.in/mf")
    return Gson().fromJson(schemeData.bodyAsText(), Array<Scheme>::class.java)
}
