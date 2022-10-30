package com.example.tbc_final.domain.use_case.preferences

import com.example.tbc_final.domain.repository.local.StepPreferencesRepository
import javax.inject.Inject

class PutStepUseCase @Inject constructor(private val stepPreferencesRepository: StepPreferencesRepository) {

    suspend fun putStep(step:String){
        return stepPreferencesRepository.putStep(step)
    }
    suspend fun putTotalStep(step:String){
        return stepPreferencesRepository.putTotalStep(step)
    }
    suspend fun putPoints(step:String){
        return stepPreferencesRepository.putPoints(step)
    }
}