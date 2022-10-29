package com.example.tbc_final.common

import retrofit2.Response

abstract class RequestHandler {
    suspend fun <T> apiCall(request:suspend () -> Response<T>): Resource<T> {
        return try {
            Resource.loading(null)
            val response = request()
            if (response.isSuccessful){
                Resource.success(response.body()!!)
            }else{
                Resource.error(response.message())
            }
        }catch (e: Exception){
            Resource.error(e.message ?: "error")
        }
    }
}