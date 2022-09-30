package com.codelytical.flybuy.presentation.di

import com.codelytical.flybuy.data.api.FlyBuyApiService
import com.codelytical.flybuy.data.db.FlyBuyDAO
import com.codelytical.flybuy.data.repository.datasource.FlyBuyLocalDataSource
import com.codelytical.flybuy.data.repository.datasource.FlyBuyRemoteDataSource
import com.codelytical.flybuy.data.repository.datasourceImpl.FlyBuyLocalDataSourceImpl
import com.codelytical.flybuy.data.repository.datasourceImpl.FlyBuyRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

	@Singleton
	@Provides
	fun providesFlyBuyLocalDataSource(flyBuyDAO: FlyBuyDAO) : FlyBuyLocalDataSource {
		return FlyBuyLocalDataSourceImpl(flyBuyDAO = flyBuyDAO)
	}

	@Provides
	@Singleton
	fun provideFlyBuyRemoteDataSource(flyBuyApiService: FlyBuyApiService): FlyBuyRemoteDataSource {
		return FlyBuyRemoteDataSourceImpl(flyBuyApiService = flyBuyApiService)
	}
}