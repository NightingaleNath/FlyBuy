package com.codelytical.flybuy.presentation.di

import com.codelytical.flybuy.domain.repository.FlyBuyRepository
import com.codelytical.flybuy.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

	@Provides
	@Singleton
	fun providesAuthUseCase(flyBuyRepository: FlyBuyRepository): AuthUseCase {
		return AuthUseCase(flyBuyRepository = flyBuyRepository)
	}
}