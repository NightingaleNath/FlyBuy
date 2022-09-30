package com.codelytical.flybuy.data.api

import com.codelytical.flybuy.data.model.modelrequest.LoginRequest
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.model.modelresponse.LoginResponse
import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import com.codelytical.flybuy.data.model.modelresponse.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface FlyBuyApiService {

	//Login user
	@POST("/api/signin")
	suspend fun loginUser(@Body login : LoginRequest): Response<LoginResponse>

	//Register user
	@POST("/api/signup")
	suspend fun registerUser(@Body user : UserRequest): Response<UserResponse>

	@GET("/api/products")
	suspend fun getProducts(@Header("x-auth-token") userToken: String): Response<ProductItem>

	@GET("products/{id}")
	suspend fun getProduct(@Path(value = "id") itemId : Int) : Response<ProductResponse>

}