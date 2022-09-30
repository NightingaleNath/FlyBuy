package com.codelytical.flybuy.domain.usecase

import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.util.Resource
import com.codelytical.flybuy.domain.repository.FlyBuyRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val flyBuyRepository: FlyBuyRepository
) {

    suspend fun getAllProducts(userToken: String) : Resource<ProductItem> {
        return flyBuyRepository.getAllProducts(userToken = userToken)
    }


}