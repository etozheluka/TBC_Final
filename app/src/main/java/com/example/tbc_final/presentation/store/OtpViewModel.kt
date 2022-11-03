package com.example.tbc_final.presentation.store

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.utils.extensions.toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OtpViewModel @Inject constructor(private val auth: FirebaseAuth):
    ViewModel() {

    private val _otpFlow = MutableSharedFlow<Task<AuthResult?>>()
    val otpFlow get() = _otpFlow.asSharedFlow()


    fun signInWithCredential(credential: PhoneAuthCredential) = viewModelScope.launch {
        val result =  auth.signInWithCredential(credential)
        _otpFlow.emit(result)
    }

     fun sendVerificationCode(number: String, activity: Activity) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(3L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(mCallBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                }
            }


            override fun onVerificationFailed(e: FirebaseException) {
            }
        }




}
