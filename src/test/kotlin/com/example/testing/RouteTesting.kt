package com.example.testing

import com.example.models.ProductApi
import com.example.models.Products
import com.example.models.Query
import com.example.models.ResponseMessage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import kotlin.test.assertEquals
import kotlin.test.Test

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RouteTesting{
    @Test
    fun testAGetAllProducts() = testApplication {
        val response = client.get("/getAllProducts")
            val userResponse = Json.decodeFromString<ResponseMessage>(response.bodyAsText())
            assertEquals(ResponseMessage("Inserted Successfully", true), userResponse)
            assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testBSearchProducts()= testApplication {
        val request=Query("MacBook Pro")
        val serializer=Json.encodeToString(request)
        val response = client.post("/searchProducts"){
            headers[HttpHeaders.ContentType]=ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<ProductApi>(response.bodyAsText())
        assertEquals(6,userResponse.products[0].id)
        assertEquals(HttpStatusCode.OK,response.status)
    }
    @Test
    fun testCFetchProducts()= testApplication {
        val request=Query("laptops")
        val serializer=Json.encodeToString(request)
        val response=client.post("/fetchProductsByCategory"){
            headers[HttpHeaders.ContentType]=ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<ProductApi>(response.bodyAsText())
        assertEquals(true,userResponse.products.isNotEmpty())
        assertEquals(HttpStatusCode.OK,response.status)
    }
    @Test
     fun testDDelete()= testApplication {
        val request= Query("smartphones")
        val serializer=Json.encodeToString(request)
        val response=client.post("/deleteProducts") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val responseUser = Json.decodeFromString<ResponseMessage>(response.bodyAsText())
        assertEquals(ResponseMessage("Deleted Successfully",true), responseUser)
        assertEquals(HttpStatusCode.OK, response.status)
    }
    @Test
    fun testEUpdate()= testApplication {
        val request=Products(1,"mac")
        val serializer=Json.encodeToString(request)
        val response=client.post("/updateProducts") {
            headers[HttpHeaders.ContentType]=ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse=Json.decodeFromString<ResponseMessage>(response.bodyAsText())
        assertEquals(ResponseMessage("Failed to update data",false),userResponse)
        assertEquals(HttpStatusCode.OK,response.status)
    }
}