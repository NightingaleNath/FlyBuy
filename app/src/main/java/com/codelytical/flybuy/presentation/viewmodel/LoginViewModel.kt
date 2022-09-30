package com.codelytical.flybuy.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelytical.flybuy.data.model.modelrequest.LoginRequest
import com.codelytical.flybuy.data.util.Resource
import com.codelytical.flybuy.data.util.SharedPreference
import com.codelytical.flybuy.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val authUseCase: AuthUseCase,
	private val sharedPrefUtil: SharedPreference,
) : ViewModel() {

	val successful: MutableLiveData<Boolean?> = MutableLiveData()
	val error: MutableLiveData<String?> = MutableLiveData()

	val loggedIn : String = sharedPrefUtil.getUserToken()

	private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

	fun login(loginRequest: LoginRequest) {
		authUseCase.loginUser(loginRequest = loginRequest).onEach { result ->
			when (result) {
				is Resource.Loading -> {
					Log.i("LoginViewModel", "I dey here, Loading")
				}
				is Resource.Error -> {
					error.postValue("${result.message}")
					successful.postValue(false)
					Log.i("LoginViewModel", "I dey here, Error ${result.message}")

				}
				is Resource.Success -> {
					successful.postValue(true)
					saveUserAccessToken("${result.data?.token}")
					Log.i("LoginViewModel", "I dey here, Success ${result.data?.id}")
				}
			}
		}.launchIn(viewModelScope)

	}

	fun navigateToPage(){
		successful.postValue(null)
		error.postValue(null)
	}
}