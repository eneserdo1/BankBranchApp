package com.app.bankbranchapp.business.repositories

import com.app.bankbranchapp.common.models.Resource
import com.app.bankbranchapp.business.models.BankListResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAllBankBranchList(): Flow<Resource<BankListResponse>>

}