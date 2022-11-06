package com.example.tbc_final.domain.use_case.room

import com.example.tbc_final.data.repository.local.FavoritesRepositoryImpl
import com.example.tbc_final.domain.repository.local.FavoritesRepository
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend fun invoke() = favoritesRepository.getFavorites()
}