package com.app.bankbranchapp.di

import com.app.bankbranchapp.business.network.ApiService
import com.app.bankbranchapp.business.remoteDataSource.RemoteDataSource
import com.app.bankbranchapp.business.repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
    ): Repository {
        return Repository(remoteDataSource)
    }
}