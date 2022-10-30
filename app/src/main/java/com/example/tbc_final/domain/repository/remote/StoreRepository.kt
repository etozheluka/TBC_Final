package com.example.tbc_final.domain.repository.remote


import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.utils.common.Resource
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStoreItems(): Flow<Resource<SneakerModel>>
}