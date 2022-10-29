package com.example.tbc_final.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.common.UiState
import com.example.tbc_final.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val signUpRepository: SignUpRepository) :
    ViewModel() {

    private val _signupFlow = MutableSharedFlow<UiState<FirebaseUser>?>()
    val signupFlow get() = _signupFlow.asSharedFlow()

    suspend fun signup(email: String, password: String) = viewModelScope.launch {
        val result = signUpRepository.signUp(email, password)
        _signupFlow.emit(result)
    }

}