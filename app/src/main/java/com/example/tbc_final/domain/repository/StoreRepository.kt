package com.example.tbc_final.domain.repository

import com.example.tbc_final.common.UiState
import com.example.tbc_final.domain.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getStoreItems(): Flow<UiState<List<Sneaker>>>
}