package com.panDetails.models

import kotlinx.serialization.Serializable

@Serializable
data class Request(val panNumber : String?)

@Serializable
data class PositiveResponse(val infold:String, val infoMsg:String, val name:String, val dateOfBirth:String)

@Serializable
data class NegativeResponse(val infold: String,val infoMsg: String)

@Serializable
data class ApiData(val status:String,val statusCode:String, val result : List<TypeDetails>)

@Serializable
data class ConfValue(val conf: Int,val value : String)

@Serializable
data class Details(val name :ConfValue,
                   val father:ConfValue,
                   val date:ConfValue,
                   val pan_no :ConfValue,
                   val date_of_issue:ConfValue,
                   val tag:String,
                   val url :String)

@Serializable
data class TypeDetails(val type:String,val details:Details)

