package com.example.tbc_final.domain.use_case.nutrition

import com.example.tbc_final.domain.repository.remote.NutritionRepository
import retrofit2.http.Query
import javax.inject.Inject

class GetNutritionUseCase @Inject constructor(private val nutritionRepository: NutritionRepository) {

    suspend fun invoke(query: String) = nutritionRepository.getNutrition(query)

}