package com.bookstore.plugins

import com.bookstore.models.Book
import com.bookstore.models.Request
import com.bookstore.models.ResponseMessage
import com.bookstore.services.BookServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val bookServices= BookServices()
    var response:ResponseMessage
    routing {
        route("/bookStore") {
            // Add a book
            post {
                val book = call.receive<Book>()
                response = bookServices.addBookService(book)
                call.respond(HttpStatusCode.Created, response)
            }
            //update a book
            put{
                val request=call.receive<Request>()
                response = bookServices.updateBookService(request.id,request.price)
                call.respond(HttpStatusCode.Accepted,response)
            }
            //delete a book
            delete {
                val request=call.receive<Request>()
                response=bookServices.deleteBookService(request.id)
                call.respond(HttpStatusCode.OK, response)
            }
            //Get all books
            get {
                call.respond(HttpStatusCode.OK,bookServices.getBooksService())
            }
        }
    }
}
