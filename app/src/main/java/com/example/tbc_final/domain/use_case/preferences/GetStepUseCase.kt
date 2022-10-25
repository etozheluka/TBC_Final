package com.example.tbc_final.domain.use_case.preferences

import com.example.tbc_final.domain.repository.StepPreferencesRepository
import javax.inject.Inject

class GetStepUseCase @Inject constructor(private val stepPreferencesRepository: StepPreferencesRepository) {

    suspend operator fun invoke():Result<String>{
        return stepPreferencesRepository.getStep()
    }
}