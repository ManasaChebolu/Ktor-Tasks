package com.scheme.services

import com.scheme.models.Response
import com.scheme.models.ResponseMessage
import com.scheme.models.Scheme
import com.scheme.plugins.NotNullOrBlankException
import com.scheme.plugins.SchemeNameNotExistException
import com.scheme.plugins.TableNotExistsException
import com.scheme.repository.SchemeRepository
import com.scheme.utlis.InfoMessage
import org.jetbrains.exposed.exceptions.ExposedSQLException

class SchemeService {
    private val schemeRepository = SchemeRepository()

    suspend fun getSchemeRepoResult(): ResponseMessage {
        try {
           schemeRepository.getSchemeDataRepo()
           return ResponseMessage(InfoMessage.SUCCESS_INSERT, true)
        } catch (e: ExposedSQLException) {
            println("ExposedSQLException occurred: ${e.message}")
            throw TableNotExistsException(ResponseMessage(InfoMessage.INVALID_TABLE_NOT_EXIST,false))
        }
    }

    suspend fun postSchemeRepoResult(schemeName : String) : Scheme {
        try {
            val response = schemeRepository.postSchemeDataRepo(schemeName)
            if (response == null)
                throw SchemeNameNotExistException(ResponseMessage(InfoMessage.INVALID_SCHEME_NAME_NOT_EXIST, false))
            else
                return response
        }catch (e: ExposedSQLException) {
            println("ExposedSQLException occurred: ${e.message}")
            throw TableNotExistsException(ResponseMessage(InfoMessage.INVALID_TABLE_NOT_EXIST,false))
        }
    }

    suspend fun postMetaDataRepoResult(schemeCode :Int,filter :String): Response {
       return try {
             schemeRepository.postMetaDataRepo(schemeCode, filter)
        }
       catch (e: NullPointerException) {
           println("NullPointerException occurred: ${e.message}")
           throw NotNullOrBlankException(ResponseMessage(InfoMessage.INVALID_SCHEME_CODE_NOT_EXIST, false))
       }
    }
}

