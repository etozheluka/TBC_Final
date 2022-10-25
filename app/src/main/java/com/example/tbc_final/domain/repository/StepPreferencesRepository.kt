package com.example.tbc_final.domain.repository

interface StepPreferencesRepository {

    suspend fun putStep(
        steps: String,
    )
    suspend fun putTotalStep(
        steps: String
    )

    suspend fun getStep():Result<String>

    suspend fun getTotalStep():Result<String>
}