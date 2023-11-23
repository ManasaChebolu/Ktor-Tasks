package com.panDetails.httpClient

import com.google.gson.Gson
import com.panDetails.models.ApiData
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

suspend fun getApiPanData(): ApiData {
    val urlData = getClient().get("https://mocki.io/v1/d9312522-1f5c-4ea8-a8dd-3f62f56f88e3")
    return Gson().fromJson(urlData.bodyAsText(),ApiData::class.java)
}


