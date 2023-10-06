package com.example.routeTesting

import com.example.models.Book
import com.example.models.BooksList
import com.example.models.Request
import com.example.models.ResponseMessage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class RouteTesting {
    @Test
    fun testAddBook() = testApplication {
        val request = Book(title = "The Hitchhiker's Guide to the Galaxy",
            authorId = "7031df72-8080-4011-a511-5c505a489b0e",
            categoryId = "8dce6014-def1-484f-87a3-b3a6e2389ecf",
            publicationDate = "1764-2-12",
            price=12.54,
            description = "A humorous science fiction series about the misadventures of an unwitting human.")
        val serializer = Json.encodeToString(request)
        val response = client.post("/bookStore"){
            headers[HttpHeaders.ContentType]= ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<ResponseMessage>(response.bodyAsText())
        assertEquals(ResponseMessage("Book Added Successfully ", true),userResponse)
        assertEquals(HttpStatusCode.Created,response.status)
    }
    @Test
    fun testUpdateBook() = testApplication {
        val request = Request(id="63fa43dc-6273-454d-89e7-eca0a8d4e0f9")
        val serializer = Json.encodeToString(request)
        val response=client.put("/bookStore") {
            headers[HttpHeaders.ContentType]=ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse =Json.decodeFromString<ResponseMessage>(response.bodyAsText())
        assertEquals(ResponseMessage("Book Updated Successfully with id: 63fa43dc-6273-454d-89e7-eca0a8d4e0f9", true),userResponse)
        assertEquals(HttpStatusCode.Accepted,response.status)
    }
    @Test
    fun testDeleteBook()= testApplication {
        val request = Request(id="63fa43dc-6273-454d-89e7-eca0a8d4e0f9")
        val serializer=Json.encodeToString(request)
        val response =client.delete("/bookStore") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializer)
        }
        val userResponse = Json.decodeFromString<ResponseMessage>(response.bodyAsText())
        assertEquals(ResponseMessage("Book deleted Successfully with id : 63fa43dc-6273-454d-89e7-eca0a8d4e0f9", true),userResponse)
        assertEquals(HttpStatusCode.OK,response.status)
    }
    @Test
    fun testGetBooks() = testApplication {
        val response=client.get("/bookStore")
        val userRequest =Json.decodeFromString<List<BooksList>>(response.bodyAsText())
        assertEquals("The Alchemist",userRequest[0].title)
        assertEquals(HttpStatusCode.OK,response.status)
    }

}