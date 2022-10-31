package com.example.tbc_final.domain.use_case.preferences

import com.example.tbc_final.domain.repository.local.StepPreferencesRepository
import javax.inject.Inject

class GetStepUseCase @Inject constructor(private val stepPreferencesRepository: StepPreferencesRepository) {

    suspend fun getStep():Result<String>{
        return stepPreferencesRepository.getStep()
    }
    suspend fun getTotalStep():Result<String>{
        return stepPreferencesRepository.getTotalStep()
    }
    suspend fun getPoints():Result<String>{
        return stepPreferencesRepository.getPoints()
    }
}