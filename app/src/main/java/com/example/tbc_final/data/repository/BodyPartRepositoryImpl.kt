package com.example.tbc_final.data.repository

import com.example.tbc_final.utils.common.RequestHandler
import com.example.tbc_final.utils.common.Resource
import com.example.tbc_final.data.remote.service.BodyPartApiInterface
import com.example.tbc_final.domain.model.BodyExercisesModel
import com.example.tbc_final.domain.repository.BodyPartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BodyPartRepositoryImpl @Inject constructor(private val api:BodyPartApiInterface):
    RequestHandler(),BodyPartRepository {

    override suspend fun getCardio() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getCardio() })
    }

    override suspend fun getLowerLegs() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getLowerLegs() })
    }
    override suspend fun getUpperLegs() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getUpperLegs() })
    }
    override suspend fun getWaist() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getWaist() })
    }
    override suspend fun getBack() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getBack() })
    }





}