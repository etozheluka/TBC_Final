package com.example.tbc_final.data.repository

import com.example.tbc_final.common.UiState
import com.example.tbc_final.domain.repository.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val  auth: FirebaseAuth):SignUpRepository {
    override suspend fun signUp(email: String, password: String): UiState<FirebaseUser> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(email).build()
            )?.await()
            return UiState.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            UiState.Error(e)
        }
    }
}
