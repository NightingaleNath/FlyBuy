package com.codelytical.flybuy.data.repository.datasource

import com.codelytical.flybuy.data.model.modelrequest.LoginRequest
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.model.modelresponse.LoginResponse
import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import com.codelytical.flybuy.data.model.modelresponse.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface FlyBuyRemoteDataSource {

	suspend fun loginUser(login : LoginRequest): Response<LoginResponse>
	suspend fun registerUser(user: UserRequest) : Response<UserResponse>
	suspend fun getAllProducts(userToken: String) : Response<ProductItem>
	suspend fun getProduct(itemId : Int) : Response<ProductResponse>

}