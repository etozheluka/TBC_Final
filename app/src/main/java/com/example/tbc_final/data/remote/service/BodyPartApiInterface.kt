package com.example.tbc_final.data.remote.service

import com.example.tbc_final.domain.model.BodyExercisesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BodyPartApiInterface {



    @GET("cardio")
    suspend fun getCardio():Response<List<BodyExercisesModel>>


    @GET("lower%20legs")
    suspend fun getLowerLegs():Response<List<BodyExercisesModel>>


    @GET("upper%20legs")
    suspend fun getUpperLegs():Response<List<BodyExercisesModel>>


    @GET("waist")
    suspend fun getWaist():Response<List<BodyExercisesModel>>


    @GET("back")
    suspend fun getBack():Response<List<BodyExercisesModel>>
}