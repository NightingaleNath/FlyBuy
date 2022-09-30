package com.codelytical.flybuy.data.db

import androidx.room.*
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface FlyBuyDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToWishlist(productResponse: ProductResponse)

    @Query("select * from wishlist")
    fun wishlistItems() : Flow<List<ProductResponse>>

    @Delete
    suspend fun deleteWishlist(productResponse: ProductResponse)

    @Query("delete from wishlist")
    suspend fun clearWishlist()


}