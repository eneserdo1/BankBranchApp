package com.app.bankbranchapp.business.repositories

import com.app.bankbranchapp.business.remoteDataSource.RemoteDataSource
import com.app.bankbranchapp.common.models.Resource
import com.app.bankbranchapp.business.models.BankListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {

    override suspend fun getAllBankBranchList(): Flow<Resource<BankListResponse>> {
        return flow {
            emit(remoteDataSource.getAllBankBranch())
        }
            .onStart { emit(Resource.loading()) }
            .flowOn(Dispatchers.IO)

    }
}