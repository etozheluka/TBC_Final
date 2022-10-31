package com.example.tbc_final.domain.use_case.auth

import com.example.tbc_final.domain.repository.remote.LoginRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun invoke(email:String,password:String) = loginRepository.login(email,password)
}