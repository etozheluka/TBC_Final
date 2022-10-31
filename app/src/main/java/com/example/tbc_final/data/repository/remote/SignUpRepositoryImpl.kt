package com.example.tbc_final.data.repository.remote

import com.example.tbc_final.domain.repository.remote.SignUpRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : SignUpRepository {
    override suspend fun signUp(email: String, password: String): FirebaseUser? {

        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            return result.await().user
        }catch (e:java.lang.Exception){
            null
        }

    }
}
