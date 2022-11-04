package com.example.tbc_final.domain.repository.local

import com.example.tbc_final.domain.model.Sneakers

interface FavoritesRepository {

    suspend fun getFavorites(): List<Sneakers>

    suspend fun insertFavoriteSneaker(sneakers: Sneakers)

    suspend fun deleteFavoriteManga(sneakers: Sneakers)

    suspend fun searchFavoriteSneaker(id: Int): Sneakers?

}