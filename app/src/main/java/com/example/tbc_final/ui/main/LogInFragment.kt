package com.example.tbc_final.ui.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.R
import com.example.tbc_final.utils.common.UiState
import com.example.tbc_final.databinding.FragmentLogInBinding
import com.example.tbc_final.ui.base.BaseFragment
import com.example.tbc_final.utils.extensions.isValidEmail
import com.example.tbc_final.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val logInViewModel by viewModels<LogInViewModel>()

    override fun onBind() {
        listener()
    }

    private fun observer(email:String,password:String) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                logInViewModel.logInFlow.collect {
                    Log.d("lukatester", "observer: ${it}")
                    when (it) {
                        is UiState.Success -> {
                            logInViewModel.logIn(
                                email = email,
                                password = password
                            )
                            toast("Asdassa")
                        }
                        is UiState.Error -> {
                            toast("1212512s")
                        }
                        is UiState.InProcess -> {
                            toast("Asdassa")
                        }
                    }
                }
            }
        }
    }

    private fun validation(){
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
                observer(email,password)
            }


        }
    }



    private fun listener() {

        binding?.apply {
            logInBtn.setOnClickListener {
//                observer()
//                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment2())
                validation()

            }
            logInTVSignUp.setOnClickListener {
                findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment())
            }
        }

    }
}
