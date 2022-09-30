package com.codelytical.flybuy.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.util.Resource
import com.codelytical.flybuy.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
	private val authUseCase: AuthUseCase,
) : ViewModel() {

	val successful: MutableLiveData<Boolean?> = MutableLiveData()
	val error: MutableLiveData<String?> = MutableLiveData()

	fun registerUser(userRequest: UserRequest) {
		authUseCase.registerUser(userRequest = userRequest).onEach { result ->
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
					Log.i("LoginViewModel", "I dey here, Success ${result.data}")
				}
			}
		}.launchIn(viewModelScope)

	}

	fun navigateToPage(){
		successful.postValue(null)
		error.postValue(null)
	}
}