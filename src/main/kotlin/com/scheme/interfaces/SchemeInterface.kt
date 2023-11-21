package com.scheme.interfaces

import com.scheme.models.Response
import com.scheme.models.Scheme


interface SchemeInterface {

    suspend fun getSchemeDataRepo()
    suspend fun postSchemeDataRepo(schemeName : String) : Scheme?
    suspend fun postMetaDataRepo(schemeCode : Int,filter : String) : Response

}

