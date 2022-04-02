package com.app.bankbranchapp.business.repositories

import com.app.bankbranchapp.business.remoteDataSource.RemoteDataSource
import com.app.bankbranchapp.common.models.Resource
import com.app.bankbranchapp.presentation.models.BankListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun getAllBankBranchList() : Flow<Resource<BankListResponse>>{
        return flow {
            emit( remoteDataSource.getAllBankBranch())
        }.flowOn(Dispatchers.IO)
    }
}