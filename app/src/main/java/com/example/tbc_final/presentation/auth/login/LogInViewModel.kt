package com.example.tbc_final.presentation.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.utils.common.UiState
import com.example.tbc_final.domain.repository.remote.LoginRepository
import com.example.tbc_final.domain.use_case.auth.LogInUseCase
import com.example.tbc_final.presentation.store.OtpFragmentDirections
import com.example.tbc_final.utils.extensions.toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val logInUseCase: LogInUseCase):
    ViewModel() {

    private val _logInFlow = MutableSharedFlow<FirebaseUser?>()
    val logInFlow get() = _logInFlow.asSharedFlow()

    suspend fun logIn(email:String,password:String) = viewModelScope.launch {
        val result = logInUseCase.invoke(email,password)
        _logInFlow.emit(result)
    }

}
