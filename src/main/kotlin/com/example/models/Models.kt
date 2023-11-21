package com.example.models
import kotlinx.serialization.Serializable

@Serializable
data class Scheme(val schemeCode :Int,val schemeName: String)

@Serializable
data class ResponseMessage(val message : String,val success: Boolean)

@Serializable
data class RequestBySchemeName(val request :RequestDataBySchemeName )

@Serializable
data class RequestDataBySchemeName(val schemeName: String?="")

@Serializable
data class RequestBySchemeCode(val request: RequestDataBySchemeCode)

@Serializable
data class RequestDataBySchemeCode(val schemeId :Int = 0, val filter:String?="")

@Serializable
data class Response(val response : ResponseData)

@Serializable
data class ResponseData(
    val fundHouse: String,
    val schemeCode: Int,
    val schemeName: String,
    val data: List<List<String>>
)

@Serializable
data class Meta(val fund_house:String, val scheme_type:String, val scheme_category:String, val scheme_code :Int, val scheme_name:String)

@Serializable
data class Data(val date :String,val nav:String)

@Serializable
data class UrlData(val meta : Meta , val data : List<Data>, val status : String)