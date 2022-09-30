package com.codelytical.flybuy.presentation.di

import com.codelytical.flybuy.BuildConfig
import com.codelytical.flybuy.data.api.FlyBuyApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	@Provides
	@Singleton
	fun providesRetrofit(): Retrofit {
		val interceptor = HttpLoggingInterceptor().apply {
			this.level = HttpLoggingInterceptor.Level.BODY
		}

		val client = OkHttpClient.Builder().apply {
			this.addInterceptor(interceptor)
				.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(25, TimeUnit.SECONDS)
		}.build()

		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.baseUrl(BuildConfig.BASE_URL)
			.build()
	}

	@Provides
	@Singleton
	fun providesFlyBuyApiService(retrofit: Retrofit): FlyBuyApiService {
		return retrofit.create(FlyBuyApiService::class.java)
	}

	@Provides
	@Singleton
	fun providesGson(): Gson = GsonBuilder().create()
}