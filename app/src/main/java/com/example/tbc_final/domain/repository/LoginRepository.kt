package com.example.tbc_final.domain.repository

import com.example.tbc_final.utils.common.UiState
import com.google.firebase.auth.AuthResult

interface LoginRepository {
    suspend fun login(email:String,password:String): UiState<AuthResult>
}