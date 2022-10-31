package com.example.tbc_final.domain.repository.remote

import com.example.tbc_final.utils.common.UiState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun login(email:String,password:String): FirebaseUser?
}