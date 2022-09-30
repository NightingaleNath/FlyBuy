package com.codelytical.flybuy.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codelytical.flybuy.data.model.modelresponse.ProductItem
import com.codelytical.flybuy.data.util.Network.isNetworkAvailable
import com.codelytical.flybuy.data.util.Resource
import com.codelytical.flybuy.data.util.SharedPreference
import com.codelytical.flybuy.domain.usecase.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private val productUseCase: ProductUseCase,
    private val sharedPreference: SharedPreference
) : AndroidViewModel(app){

    val products : MutableLiveData<Resource<ProductItem>> = MutableLiveData()

    val loggedIn : String = sharedPreference.getUserToken()

    fun getAllProducts(userToken: String) = viewModelScope.launch(IO) {
        products.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)){
                val apiResult = productUseCase.getAllProducts(userToken = userToken)
                products.postValue(apiResult)
            }else{
                products.postValue(Resource.Error(message = "Internet not available"))
            }
        }catch (e : Exception){
            products.postValue(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }


}