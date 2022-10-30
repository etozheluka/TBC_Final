package com.example.tbc_final.data.remote.service

import com.example.tbc_final.domain.model.SneakerModel
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {
    @GET("443dcd7e-2d94-449f-88e7-2dfaea0aefac")
    suspend fun getStoreApi():Response<SneakerModel>
}