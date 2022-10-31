package com.example.tbc_final.domain.repository.remote


import com.example.tbc_final.utils.common.Resource
import com.example.tbc_final.domain.model.BodyExercisesModel
import kotlinx.coroutines.flow.Flow

interface BodyPartRepository {
    suspend fun getCardio(): Flow<Resource<List<BodyExercisesModel>>>
    suspend fun getLowerLegs(): Flow<Resource<List<BodyExercisesModel>>>
    suspend fun getUpperLegs(): Flow<Resource<List<BodyExercisesModel>>>
    suspend fun getWaist(): Flow<Resource<List<BodyExercisesModel>>>
    suspend fun getBack(): Flow<Resource<List<BodyExercisesModel>>>

}