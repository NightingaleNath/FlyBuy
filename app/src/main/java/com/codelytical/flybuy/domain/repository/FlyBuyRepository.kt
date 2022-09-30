package com.codelytical.flybuy.domain.repository

import com.codelytical.flybuy.data.model.modelrequest.LoginRequest
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.model.modelresponse.LoginResponse
import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import com.codelytical.flybuy.data.model.modelresponse.UserResponse
import com.codelytical.flybuy.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlyBuyRepository {

	suspend fun loginUser(login: LoginRequest) : Resource<LoginResponse>
	suspend fun registerUser(user : UserRequest) : Resource<UserResponse>

	suspend fun getAllProducts(userToken: String) : Resource<ProductItem>
	suspend fun getProduct(itemId : Int) : Resource<ProductResponse>

	suspend fun addToWishlist(productResponse: ProductResponse)
	fun getWishlistItems() : Flow<List<ProductResponse>>
	suspend fun deleteWishlistItem(productResponse: ProductResponse)
	suspend fun clearWishlist()
}