package com.example.tbc_final.domain.repository

interface StepPreferencesRepository {

    suspend fun putStep(
        steps: String,
    )

    suspend fun getStep():Result<String>
}