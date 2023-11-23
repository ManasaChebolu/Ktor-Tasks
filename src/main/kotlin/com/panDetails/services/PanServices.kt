package com.panDetails.services

import com.panDetails.models.NegativeResponse
import com.panDetails.models.PositiveResponse
import com.panDetails.plugins.TableNotExistException
import com.panDetails.repositories.PanRepository
import com.panDetails.utlis.InfoMessage
import org.jetbrains.exposed.exceptions.ExposedSQLException

class PanServices {
    private val panRepo = PanRepository()

    suspend fun panRepoResult(panNumber : String) : PositiveResponse {
        try {
            return panRepo.postPanDetailsRepo(panNumber)
        }catch (e: ExposedSQLException) {
            println("ExposedSQLException occurred: ${e.message}")
            throw TableNotExistException(NegativeResponse(InfoMessage.INVALID_IN_FOLD,
                InfoMessage.INVALID_TABLE_NOT_EXIST))
        }
    }
}

