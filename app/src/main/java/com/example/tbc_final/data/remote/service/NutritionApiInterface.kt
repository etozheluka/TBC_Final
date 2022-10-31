package com.example.tbc_final.data.remote.service

import com.example.tbc_final.domain.model.NutritionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NutritionApiInterface {

    @Headers("X-Api-Key: NIIQGy+t9iGu6hiLDQ6VBw==koJ3LPgNNmDrrWLD")
    @GET("/v1/nutrition")
    suspend fun getNutrition(@Query("query") query: String):Response<NutritionModel>
}