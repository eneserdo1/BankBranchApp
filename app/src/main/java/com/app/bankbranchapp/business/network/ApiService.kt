package com.app.bankbranchapp.business.network

import com.app.bankbranchapp.common.constants.NetworkConstants
import com.app.bankbranchapp.presentation.models.BankListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(NetworkConstants.API_ENDPOINT)
    suspend fun getAllBankList():Response<BankListResponse>

}