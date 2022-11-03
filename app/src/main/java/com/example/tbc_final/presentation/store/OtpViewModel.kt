package com.example.tbc_final.presentation.store

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.utils.extensions.toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(private val auth: FirebaseAuth):
    ViewModel() {


     fun sendVerificationCode(number: String, activity: Activity,callback:PhoneAuthProvider.OnVerificationStateChangedCallbacks) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number)
            .setTimeout(3L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    fun verifyCode(id:String,code: String):PhoneAuthCredential {
        return PhoneAuthProvider.getCredential(id, code)
    }





}
