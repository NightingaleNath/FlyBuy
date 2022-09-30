package com.codelytical.flybuy.presentation.di

import android.app.Application
import com.codelytical.flybuy.data.util.SharedPreference
import com.codelytical.flybuy.domain.usecase.AuthUseCase
import com.codelytical.flybuy.domain.usecase.ProductUseCase
import com.codelytical.flybuy.presentation.viewmodel.HomeViewModel
import com.codelytical.flybuy.presentation.viewmodel.LoginViewModel
import com.codelytical.flybuy.presentation.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

	@Provides
	@Singleton
	fun providesLoginViewModel(authUseCase: AuthUseCase, sharedPreference: SharedPreference) : LoginViewModel{
		return LoginViewModel(authUseCase,sharedPreference)
	}

	@Singleton
	@Provides
	fun providesHomeViewModel(app : Application, productUseCase: ProductUseCase, sharedPreference: SharedPreference) : HomeViewModel {
		return HomeViewModel(app, productUseCase, sharedPreference)
	}

	@Singleton
	@Provides
	fun providesSplashViewModel(sharedPreference: SharedPreference) : SplashViewModel{
		return SplashViewModel(sharedPreference)
	}

}