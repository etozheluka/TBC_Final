package com.example.tbc_final.domain.repository.remote

import com.example.tbc_final.utils.common.UiState
import com.google.firebase.auth.FirebaseUser

interface SignUpRepository {
    suspend fun signUp(email:String, password:String): FirebaseUser?
}