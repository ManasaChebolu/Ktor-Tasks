package com.panDetails.routeTesting

import com.panDetails.models.PositiveResponse
import com.panDetails.models.Request
import com.panDetails.utlis.ApiEndPoints
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import io.ktor.client.request.setBody
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import org.junit.Test
import kotlin.test.assertEquals

class RouteTesting {

    @Test
    fun panDetailsSuccessRouteTesting() = testApplication {
        val request = Request("DMIPB1989Q")
        val serializer= Json.encodeToString(request)
        val response = client.post(ApiEndPoints.OCR_VERIFICATION) {
            headers[HttpHeaders.ContentType]= ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<PositiveResponse>(response.bodyAsText())
        assertEquals( "22/06/1997",userResponse.dateOfBirth)
        assertEquals(HttpStatusCode.OK,response.status)
    }
}

