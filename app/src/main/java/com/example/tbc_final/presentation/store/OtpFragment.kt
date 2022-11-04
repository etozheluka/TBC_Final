package com.example.tbc_final.presentation.store

import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.databinding.FragmentOtpBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.extensions.toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class OtpFragment : BaseFragment<FragmentOtpBinding>(FragmentOtpBinding::inflate) {
    private var verificationId: String? = null
    private lateinit var mAuth: FirebaseAuth
    private val otpViewModel: OtpViewModel by viewModels()

    override fun onBind() {
        mAuth = FirebaseAuth.getInstance()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding?.idBtnGetOtp?.setOnClickListener {
            if (TextUtils.isEmpty(binding?.idEdtPhoneNumber?.text.toString())) {
                toast("Please enter a valid phone number.")
            } else {
                val phone = GEO + binding?.idEdtPhoneNumber?.text.toString()
                otpViewModel.sendVerificationCode(phone, requireActivity(), mCallBack)

            }
        }
        binding?.back?.setOnClickListener {
            binding?.apply {
                back.visibility = View.GONE
                idEdtPhoneNumber.visibility = View.VISIBLE
                idBtnGetOtp.visibility = View.VISIBLE
                idEdtOtp.visibility = View.GONE
                idBtnVerify.visibility = View.GONE
            }

        }

        binding?.idBtnVerify?.setOnClickListener {
            if (TextUtils.isEmpty(binding?.idEdtOtp?.text.toString())) {
                toast("Please enter OTP")
            } else {
                signInWithCredential(
                    otpViewModel.verifyCode(
                        verificationId!!,
                        binding?.idEdtOtp?.text.toString()
                    )
                )
            }
        }

    }




    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                findNavController().navigate(OtpFragmentDirections.actionOtpFragmentToOrderFinishFragment())
            } else {
                toast(task.exception!!.message)
            }
        }

    }

    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeSent(
                p0: String,
                p1: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(p0, p1)
                verificationId = p0
                binding?.apply {
                    back.visibility = View.VISIBLE
                    idEdtPhoneNumber.visibility = View.GONE
                    idBtnGetOtp.visibility = View.GONE
                    idEdtOtp.visibility = View.VISIBLE
                    idBtnVerify.visibility = View.VISIBLE

                }

            }

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {}

            override fun onVerificationFailed(p0: FirebaseException) {}

        }


    companion object {
        private const val GEO = "+995"
    }
}