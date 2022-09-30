package com.codelytical.flybuy.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.codelytical.flybuy.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
	private val sharedPreference: SharedPreference
) : ViewModel() {
	val loggedIn : Boolean = sharedPreference.userIsLoggedIn()
}