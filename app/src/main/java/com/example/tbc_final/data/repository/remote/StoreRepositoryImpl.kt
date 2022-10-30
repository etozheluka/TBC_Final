package com.example.tbc_final.data.repository.remote


import com.example.tbc_final.data.remote.service.StoreApi
import com.example.tbc_final.domain.repository.remote.StoreRepository
import com.example.tbc_final.utils.common.RequestHandler
import com.example.tbc_final.utils.common.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val storeApi: StoreApi):RequestHandler(), StoreRepository {
    override suspend fun getStoreItems() = flow {
        emit(Resource.loading())
        emit(apiCall { storeApi.getStoreApi() })
    }
}