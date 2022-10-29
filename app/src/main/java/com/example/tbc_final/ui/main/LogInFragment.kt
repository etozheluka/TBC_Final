package com.example.tbc_final.ui.main
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.common.UiState
import com.example.tbc_final.common.extensions.isValidEmail
import com.example.tbc_final.common.extensions.toast
import com.example.tbc_final.databinding.FragmentLogInBinding
import com.example.tbc_final.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val logInViewModel by viewModels<LogInViewModel>()

    override fun start() {
        observer()
        listener()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                logInViewModel.logInFlow.collect {
                    when (it) {
                        is UiState.Success -> if (validation()) {
                            logInViewModel.logIn(
                                email = binding.logInEmail.text.toString(),
                                password = binding.logInPassword.text.toString()
                            )
                        }
                        is UiState.Error -> {
                        }
                        is UiState.InProcess -> {
                        }
                    }
                }
            }
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.logInEmail.text.isNullOrEmpty()) {
            isValid = false
            toast("")
        } else {
            if (!binding.logInEmail.text.toString().isValidEmail()) {
                isValid = false
                toast("")
            }
        }
        if (binding.logInPassword.text.isNullOrEmpty()) {
            isValid = false
            toast("")
        } else {
            if (binding.logInPassword.text.toString().length < 8) {
                isValid = false
                toast("")
            }
        }
        return isValid
    }


    private fun listener() {
        binding.logInBtn.setOnClickListener {
            findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment2())
        }
    }
}
