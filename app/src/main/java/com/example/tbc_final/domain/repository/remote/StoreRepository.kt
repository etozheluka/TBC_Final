package com.example.tbc_final.domain.repository.remote


import com.example.tbc_final.domain.model.Sneaker
import com.example.tbc_final.utils.common.UiState
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStoreItems(): Flow<UiState<List<Sneaker>>>
}