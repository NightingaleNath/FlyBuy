package com.codelytical.flybuy.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codelytical.flybuy.R
import com.codelytical.flybuy.data.model.modelrequest.UserRequest
import com.codelytical.flybuy.data.util.Utils
import com.codelytical.flybuy.data.util.Utils.validateRegisterRequest
import com.codelytical.flybuy.databinding.FragmentSignUpBinding
import com.codelytical.flybuy.presentation.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {


	val viewModel: RegisterViewModel by viewModels()
	private lateinit var signUpBinding: FragmentSignUpBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_sign_up, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		signUpBinding = FragmentSignUpBinding.bind(view)

		signUpBinding.registerButton.setOnClickListener {
			val name = signUpBinding.fullName.editableText.toString()
			val email = signUpBinding.email.editableText.toString()
			val address = signUpBinding.address.editableText.toString()
			val password = signUpBinding.registerPassword.editableText.toString()

			val result = validateRegisterRequest(name, email, password)

			if (result.successful) {
				signUpBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
				signUpBinding.registerButton.isEnabled = false

				val userRequest = UserRequest(address = address, email = email, name = name, password = password)

				viewModel.registerUser(userRequest = userRequest)

				viewModel.successful.observe(viewLifecycleOwner) { successful ->
					if (successful == true){
						signUpBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
						signUpBinding.registerButton.isEnabled = true
						findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
						viewModel.navigateToPage()
					}else if(successful == false){
						signUpBinding.loginProgress.loadingProgress.visibility = View.VISIBLE
						signUpBinding.registerButton.isEnabled = true
						Snackbar.make(signUpBinding.registerButton,"${viewModel.error.value}",Snackbar.LENGTH_SHORT).show()
						viewModel.navigateToPage()
					}
				}

			} else {
				Snackbar.make(signUpBinding.registerButton,"${result.error}", Snackbar.LENGTH_SHORT).show()
			}

		}

		signUpBinding.registerSignin.setOnClickListener {
			findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
		}
	}
}