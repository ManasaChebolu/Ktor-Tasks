package com.example.interfaces

import com.example.models.Response
import com.example.models.Scheme


interface SchemeInterface {

    suspend fun getSchemeDataRepo()
    suspend fun postSchemeDataRepo(schemeName : String) : Scheme?
    suspend fun postMetaDataRepo(schemeCode : Int,filter : String) : Response

}