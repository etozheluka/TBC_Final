package com.example.tbc_final.ui.main


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.databinding.FragmentRegisterBinding
import com.example.tbc_final.ui.base.BaseFragment
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
                } ?: toast("dafaildi")
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
            viewLifecycleOwner.lifecycleScope.launch {
                if (validation()) {
                    signUpViewModel.signup(
                        email = binding?.signUpEmail?.text.toString(),
                        password = binding?.signUpPassword?.text.toString()
                    )

                }
            }


        }
    }
}
