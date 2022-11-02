package com.example.tbc_final.data.repository.local

import com.example.tbc_final.data.remote.service.StoreApi
import com.example.tbc_final.mapper.DataMapper
import com.example.tbc_final.mapper.Sneakers
import javax.inject.Inject

class SneakersRepository @Inject constructor(
    private val sneakersListService: StoreApi
) {

    suspend fun getSneakers(): List<Sneakers> {
        return DataMapper.SneakerModelListToSneakersList(sneakersListService.getStoreApi().body()?.sneakers)
    }
}