package com.example.services

import com.example.models.Response
import com.example.models.ResponseMessage
import com.example.models.Scheme
import com.example.plugins.NotNullOrBlankException
import com.example.plugins.SchemeNameNotExistException
import com.example.plugins.TableNotExistsException
import com.example.repository.SchemeRepository
import com.example.utlis.InfoMessage
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.sql.SQLException
import java.util.logging.Logger

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

