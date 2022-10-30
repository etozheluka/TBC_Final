package com.example.tbc_final.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.common.UiState
import com.example.tbc_final.domain.repository.LoginRepository
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {
    private val _logInFlow = MutableSharedFlow<UiState<AuthResult>>()
    val logInFlow get() = _logInFlow.asSharedFlow()

    suspend fun logIn(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        _logInFlow.emit(loginRepository.login(email, password))
    }
}