package com.example.tbc_final.domain.use_case.exercises

import com.example.tbc_final.domain.repository.BodyPartRepository
import javax.inject.Inject

class GetBodyPartsUseCase @Inject constructor(private val bodyPartRepository: BodyPartRepository) {

    suspend fun getCardio() = bodyPartRepository.getCardio()
    suspend fun getLowerLegs() = bodyPartRepository.getLowerLegs()
    suspend fun getUpperLegs() = bodyPartRepository.getUpperLegs()
    suspend fun getWaist() = bodyPartRepository.getWaist()
    suspend fun getBack() = bodyPartRepository.getBack()

}