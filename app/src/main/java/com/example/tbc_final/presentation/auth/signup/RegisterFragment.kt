package com.example.tbc_final.presentation.auth.signup


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentRegisterBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.extensions.isValidEmail
import com.example.tbc_final.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val signUpViewModel by viewModels<RegisterViewModel>()


    override fun onBind() {
        listener()
        observer()
    }


    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.signupFlow.collect {
                it?.let {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
                } ?: binding?.emailInputLayout?.apply {
                    error = context.getString(R.string.registered)
                }
            }
        }
    }

    private fun validation() {
        binding?.apply {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || !email.isValidEmail()) {
                emailInputLayout.error = getString(R.string.valid_mail)
            } else {
                emailInputLayout.error = null
            }
            if (password.isEmpty()) {
                passwordInputLayout.error = getString(R.string.valid_password)
            } else {
                passwordInputLayout.error = null

            }
            if (email.isValidEmail() || password.isNotEmpty()) {
                registration(email,password)
            }

        }
    }

    private fun listener() {
        binding?.signUpBtn?.setOnClickListener {
            validation()
        }
    }

    private fun registration(email:String,password:String){
        viewLifecycleOwner.lifecycleScope.launch {
            signUpViewModel.signup(
                email = email,
                password = password
            )
        }
    }
}
