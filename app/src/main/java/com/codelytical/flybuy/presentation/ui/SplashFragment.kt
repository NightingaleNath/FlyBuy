package com.codelytical.flybuy.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.codelytical.flybuy.R
import com.codelytical.flybuy.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

	@Inject
	lateinit var viewModel: SplashViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_splash, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		Handler(Looper.getMainLooper()).postDelayed({
			//check if the user is logged in
			if (viewModel.loggedIn){
				//navigate to the home fragment
				findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
			}else{
				//navigate to the login fragment
				findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
			}
		}, 2000L)
	}
}