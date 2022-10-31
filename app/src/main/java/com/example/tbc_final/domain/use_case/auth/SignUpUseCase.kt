package com.example.tbc_final.domain.use_case.auth

import com.example.tbc_final.domain.repository.remote.LoginRepository
import com.example.tbc_final.domain.repository.remote.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val signUpRepository: SignUpRepository) {

    suspend fun invoke(email:String,password:String) = signUpRepository.signUp(email, password)
}