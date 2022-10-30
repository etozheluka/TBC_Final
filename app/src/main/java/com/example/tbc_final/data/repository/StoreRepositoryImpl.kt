package com.example.tbc_final.data.repository

import com.example.tbc_final.common.UiState
import com.example.tbc_final.data.remote.service.StoreApi
import com.example.tbc_final.domain.model.Sneaker
import com.example.tbc_final.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeApi: StoreApi):StoreRepository {
    override suspend fun getStoreItems(): Flow<UiState<List<Sneaker>>> {
        return flow {
            emit(UiState.InProcess())
            val response = storeApi.getStoreApi()
            if (response.isSuccessful && response.body() != null) {
                emit(UiState.Success(response.body()!!))
            } else
                emit(UiState.Error(Throwable("error")))
        }
    }
}