package com.example.tbc_final.domain.repository.remote

import com.example.tbc_final.domain.model.NutritionModel
import com.example.tbc_final.utils.common.Resource
import kotlinx.coroutines.flow.Flow

interface NutritionRepository {
    suspend fun getNutrition(query:String): Flow<Resource<NutritionModel>>
}