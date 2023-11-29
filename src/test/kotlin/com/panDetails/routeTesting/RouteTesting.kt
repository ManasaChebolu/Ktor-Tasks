package com.panDetails.routeTesting

import com.panDetails.models.PositiveResponse
import com.panDetails.models.Request
import com.panDetails.utils.H2Database
import com.panDetails.utlis.ApiEndPoints
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.testing.testApplication
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.After
import org.junit.BeforeClass
import org.junit.Test
import kotlin.test.assertEquals


class RouteTesting {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            H2Database.init()
        }
    }

    @Test
    fun routeTesting() {
        testApplication {
            environment {
                config = ApplicationConfig("test.conf")
            }
            val client = createClient {
                install(ContentNegotiation) {
                    json()
                }
            }
            panDetailsSuccessRouteTesting(client)
        }
    }

    @After
    fun drop() {
        H2Database.dropTestDb()
    }


    private suspend fun panDetailsSuccessRouteTesting(client: HttpClient) {
        val request = Request("DMIPB1989Q")
        val serializer = Json.encodeToString(request)
        val response = client.post(ApiEndPoints.OCR_VERIFICATION) {
            contentType(ContentType.Application.Json)
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<PositiveResponse>(response.bodyAsText())
        assertEquals("22/06/1997", userResponse.dateOfBirth)
        assertEquals(HttpStatusCode.OK, response.status)
    }
}

