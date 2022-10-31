package com.example.tbc_final.presentation.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.domain.repository.remote.SignUpRepository
import com.example.tbc_final.domain.use_case.auth.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :
    ViewModel() {

    private val _signupFlow = MutableSharedFlow<FirebaseUser?>()
    val signupFlow get() = _signupFlow.asSharedFlow()

    suspend fun signup(email: String, password: String) = viewModelScope.launch {
        val result = signUpUseCase.invoke(email, password)
       _signupFlow.emit(result)
    }

}