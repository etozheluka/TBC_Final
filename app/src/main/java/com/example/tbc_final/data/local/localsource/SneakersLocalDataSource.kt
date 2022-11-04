package com.example.tbc_final.data.local.localsource

import com.example.tbc_final.data.local.dao.FavoritesDao
import com.example.tbc_final.data.local.entity.FavoritesEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SneakersLocalDataSource @Inject constructor(
    private val favoritesDao: FavoritesDao
) {
    suspend fun getFavorites(): List<FavoritesEntity> {
        return favoritesDao.getFavoriteSneakers()
    }

    suspend fun insertFavoriteSneaker(favoritesEntity: FavoritesEntity) {
        return favoritesDao.insertFavoriteSneaker(favoritesEntity)
    }

    suspend fun deleteFavoriteSneaker(favoritesEntity: FavoritesEntity) {
        return favoritesDao.deleteFavoriteSneaker(favoritesEntity)
    }

    suspend fun searchFavoriteSneaker(id: Int): FavoritesEntity? {
        return favoritesDao.searchSneaker(id)
    }
}