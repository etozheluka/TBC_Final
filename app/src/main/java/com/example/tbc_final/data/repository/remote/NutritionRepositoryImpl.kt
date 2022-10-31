package com.example.tbc_final.data.repository.remote

import com.example.tbc_final.data.remote.service.NutritionApiInterface
import com.example.tbc_final.domain.model.NutritionModel
import com.example.tbc_final.domain.repository.remote.NutritionRepository
import com.example.tbc_final.utils.common.RequestHandler
import com.example.tbc_final.utils.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NutritionRepositoryImpl @Inject constructor(private val api: NutritionApiInterface) :
    RequestHandler(), NutritionRepository {

    override suspend fun getNutrition(query:String) = flow  {
        emit(Resource.loading())
        emit(apiCall { api.getNutrition(query) })
    }
}