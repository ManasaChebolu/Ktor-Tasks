package com.panDetails.repositories

import com.panDetails.databaseFactory.insertData
import com.panDetails.httpClient.getApiPanData
import com.panDetails.interfaces.PanInterface
import com.panDetails.models.NegativeResponse
import com.panDetails.models.PositiveResponse
import com.panDetails.plugins.OCRFailedException
import com.panDetails.plugins.PanNumberNotExistException
import com.panDetails.utlis.InfoMessage

class PanRepository : PanInterface {

    override suspend fun postPanDetailsRepo(panNumber : String):PositiveResponse {
        val urlData = getApiPanData()
        if(urlData.status == "success" ) {
            if (urlData.result[0].details.pan_no.value == panNumber) {
                insertData(urlData)
                return PositiveResponse(
                    InfoMessage.VALID_IN_FOLD,
                    urlData.status,
                    urlData.result[0].details.name.value + " " + urlData.result[0].details.father.value,
                    urlData.result[0].details.date.value
                )
            } else
                throw PanNumberNotExistException(
                    NegativeResponse(
                        InfoMessage.INVALID_IN_FOLD,
                        InfoMessage.INVALID_PAN_NO_NOT_EXISTS
                    )
                )
        }
        else
            throw OCRFailedException(NegativeResponse(InfoMessage.INVALID_IN_FOLD,
                                                         InfoMessage.INVALID_INFO_MSG))

    }
}


