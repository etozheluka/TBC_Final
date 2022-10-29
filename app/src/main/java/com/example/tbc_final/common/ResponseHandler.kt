package com.example.tbc_final.common

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

open class ResponseHandler {
    fun <T> exceptions(e:Throwable):UiState<T>{
        return when(e){
            is FirebaseAuthInvalidCredentialsException -> UiState.Error(Throwable(""))
            is FirebaseAuthWeakPasswordException -> UiState.Error(Throwable(""))
            is FirebaseAuthInvalidUserException -> UiState.Error(Throwable(""))
            is FirebaseAuthUserCollisionException -> UiState.Error(Throwable(""))
            is FirebaseNetworkException-> UiState.Error(Throwable(""))
            is IllegalArgumentException -> UiState.Error(Throwable(""))
            else->UiState.Error(Throwable(e.message))
        }
    }

    fun <T> success(data: T):UiState<T>{
        return UiState.Success(data)
    }
}