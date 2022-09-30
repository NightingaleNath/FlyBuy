package com.codelytical.flybuy.data.repository.datasource

import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import kotlinx.coroutines.flow.Flow

interface FlyBuyLocalDataSource {

    suspend fun addToWishlist(productResponse: ProductResponse)
    fun getWishlistItems() : Flow<List<ProductResponse>>
    suspend fun deleteWishlistItem(productResponse: ProductResponse)
    suspend fun clearWishlist()

}