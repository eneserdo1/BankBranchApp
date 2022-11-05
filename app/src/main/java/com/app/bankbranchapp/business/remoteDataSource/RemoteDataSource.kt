package com.app.bankbranchapp.business.remoteDataSource

import com.app.bankbranchapp.business.network.ApiService
import com.app.bankbranchapp.common.models.Resource
import com.app.bankbranchapp.common.utils.getResponse
import com.app.bankbranchapp.business.models.BankListResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllBankBranch(): Resource<BankListResponse> {
        return getResponse(
            request = { apiService.getAllBankList() },
            defaultErrorMessage = "Error Fetching Bank Branch List"
        )
    }
}