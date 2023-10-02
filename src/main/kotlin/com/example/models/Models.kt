package com.example.models

import kotlinx.serialization.Serializable

// class that represent product model
@Serializable
data class Products(val id:Int,
                    var title:String= "null",
                    var description:String = "null",
                    var price:Int=0,
                    var discountPercentage:Double= 0.0,
                    var rating:Double=0.0,
                    var stock:Int=0,
                    var brand:String="null",
                    var category:String="null",
                    var thumbnail:String="null",
                    var images:MutableList<String> = mutableListOf())

//class model for list of products
@Serializable
data class ProductApi(val products:List<Products>)

//class model to response message
@Serializable
data class ResponseMessage(val Message:String,val success: Boolean)

@Serializable
data class Query(val query:String)