package com.example.tbc_final.ui.main


import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.common.UiState
import com.example.tbc_final.common.extensions.isValidEmail
import com.example.tbc_final.common.extensions.toast
import com.example.tbc_final.databinding.FragmentRegisterBinding
import com.example.tbc_final.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val signUpViewModel by viewModels<RegisterViewModel>()


    override fun start() {
        listener()
    }


    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.signupFlow.collect {
                    when (it) {
                        is UiState.Success -> if (validation()) {
                            signUpViewModel.signup(
                                email = binding?.signUpEmail?.text.toString(),
                                password = binding?.signUpPassword?.text.toString()
                            )
                        }
                        is UiState.Error -> {
                        }
                        is UiState.InProcess -> {
                        }
                        null -> {}
                    }
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding?.signUpEmail?.text.isNullOrEmpty()) {
            isValid = false
            toast("")
        } else {
            if (!binding?.signUpEmail?.text.toString().isValidEmail()) {
                isValid = false
                toast("")
            }
        }
        if (binding?.signUpPassword?.text.isNullOrEmpty()) {
            isValid = false
            toast("")
        } else {
            if (binding?.signUpPassword?.text.toString().length < 8) {
                isValid = false
                toast("")
            }
        }
        return isValid
    }


    private fun listener() {
        binding?.signUpBtn?.setOnClickListener {
            observer()
//              findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment())
        }


    }
}
