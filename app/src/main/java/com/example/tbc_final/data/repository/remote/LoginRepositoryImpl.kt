package com.example.tbc_final.data.repository.remote


import androidx.navigation.fragment.findNavController
import com.example.tbc_final.utils.common.FirebaseServiceHandler
import com.example.tbc_final.utils.common.UiState
import com.example.tbc_final.domain.repository.remote.LoginRepository
import com.example.tbc_final.utils.extensions.toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    FirebaseServiceHandler(), LoginRepository {
    override suspend fun login(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password)
            result.await().user
        } catch (e: java.lang.Exception) {
            null
        }

    }
}




