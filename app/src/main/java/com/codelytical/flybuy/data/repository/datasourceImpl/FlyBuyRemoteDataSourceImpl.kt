package com.codelytical.flybuy.data.repository.datasourceImpl

import com.codelytical.flybuy.data.api.FlyBuyApiService
import com.codelytical.flybuy.data.model.modelrequest.LoginRequest
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.model.modelresponse.LoginResponse
import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import com.codelytical.flybuy.data.model.modelresponse.UserResponse
import com.codelytical.flybuy.data.repository.datasource.FlyBuyLocalDataSource
import com.codelytical.flybuy.data.repository.datasource.FlyBuyRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class FlyBuyRemoteDataSourceImpl @Inject constructor(
	private val flyBuyApiService: FlyBuyApiService
) : FlyBuyRemoteDataSource {

	override suspend fun loginUser(login: LoginRequest): Response<LoginResponse> {
		return flyBuyApiService.loginUser(login = login)
	}

	override suspend fun registerUser(user: UserRequest): Response<UserResponse> {
		return flyBuyApiService.registerUser(user = user)
	}

	override suspend fun getAllProducts(userToken: String): Response<ProductItem> {
		return flyBuyApiService.getProducts(userToken = userToken)
	}

	override suspend fun getProduct(itemId: Int): Response<ProductResponse> {
		return flyBuyApiService.getProduct(itemId = itemId)
	}

}