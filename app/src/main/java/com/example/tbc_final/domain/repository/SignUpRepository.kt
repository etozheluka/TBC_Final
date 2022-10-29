package com.example.tbc_final.domain.repository

import com.example.tbc_final.common.UiState
import com.google.firebase.auth.FirebaseUser

interface SignUpRepository {
    suspend fun signUp(email:String, password:String): UiState<FirebaseUser>
}