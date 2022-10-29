package com.example.tbc_final.data.remote.service

import com.example.tbc_final.domain.model.BodyExercisesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BodyPartApiInterface {

    //THANKS FOR BEST API ( NO )
    // QUERY პარამეტრები არ გააჩნია >_<

    @Headers(
        "X-RapidAPI-Key: e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("https://exercisedb.p.rapidapi.com/exercises/bodyPart/cardio")
    suspend fun getCardio():Response<List<BodyExercisesModel>>

    @Headers(
        "X-RapidAPI-Key: e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("https://exercisedb.p.rapidapi.com/exercises/bodyPart/lower%20legs")
    suspend fun getLowerLegs():Response<List<BodyExercisesModel>>

    @Headers(
        "X-RapidAPI-Key: e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("https://exercisedb.p.rapidapi.com/exercises/bodyPart/upper%20legs")
    suspend fun getUpperLegs():Response<List<BodyExercisesModel>>

    @Headers(
        "X-RapidAPI-Key: e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("https://exercisedb.p.rapidapi.com/exercises/bodyPart/waist")
    suspend fun getWaist():Response<List<BodyExercisesModel>>

    @Headers(
        "X-RapidAPI-Key: e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f",
        "X-RapidAPI-Host: exercisedb.p.rapidapi.com"
    )
    @GET("https://exercisedb.p.rapidapi.com/exercises/bodyPart/back")
    suspend fun getBack():Response<List<BodyExercisesModel>>
}