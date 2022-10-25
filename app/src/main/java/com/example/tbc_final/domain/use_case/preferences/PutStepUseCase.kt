package com.example.tbc_final.domain.use_case.preferences

import com.example.tbc_final.domain.repository.StepPreferencesRepository
import javax.inject.Inject

class PutStepUseCase @Inject constructor(private val stepPreferencesRepository: StepPreferencesRepository) {

    suspend fun invoke(step:String){
        return stepPreferencesRepository.putStep(step)
    }
}