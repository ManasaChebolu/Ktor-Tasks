package com.example.models

import kotlinx.serialization.Serializable

//data class for book
@Serializable
data class Book(
    val title:String?,
    val authorId: String?,
    val categoryId:String?=null,
    val publicationDate:String?=null,
    val price:Double,
    var description:String? = null
)

//data class for author
@Serializable
data class Author(var authorId: String?=null,val authorName: String )

//data class for category
@Serializable
data class Category(var categoryId: String?=null,val categoryName: String )

//data class to get the data
@Serializable
data class BooksList(val id: String,
                     val title: String,
                     val author: List<Author>,
                     val category:List<Category>,
                     val publicationDate:String,
                     val price:Double,
                     var description:String?
    )

//data class for response message
@Serializable
data class ResponseMessage(val message:String,val success:Boolean)

//data class for request
@Serializable
data class Request(var id:String?=null, val price: Double =0.0)