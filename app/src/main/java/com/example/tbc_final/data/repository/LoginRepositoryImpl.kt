package com.example.tbc_final.data.repository

import android.util.Log
import com.example.tbc_final.utils.common.FirebaseServiceHandler
import com.example.tbc_final.utils.common.UiState
import com.example.tbc_final.domain.repository.LoginRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    FirebaseServiceHandler(), LoginRepository {
    override suspend fun login(email: String, password: String): UiState<AuthResult> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Log.d("lukatester", "login: aasdasd")
                val result = auth.signInWithEmailAndPassword(email, password).await()
                success(result)
            } catch (e: Exception) {
                exceptions(e)
            }
        }
}