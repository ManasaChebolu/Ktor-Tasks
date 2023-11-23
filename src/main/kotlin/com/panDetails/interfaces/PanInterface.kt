package com.panDetails.interfaces

import com.panDetails.models.PositiveResponse

interface PanInterface {

    suspend fun postPanDetailsRepo(panNumber: String) : PositiveResponse

}
