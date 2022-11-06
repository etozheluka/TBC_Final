package com.example.tbc_final.data.local.dao

import androidx.room.*
import com.example.tbc_final.data.local.entity.FavoritesEntity

@Dao
interface  FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteSneaker(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorite_sneakers")
    suspend fun getFavoriteSneakers(): List<FavoritesEntity>

    @Delete
    suspend fun deleteFavoriteSneaker(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorite_sneakers WHERE id = :id")
    suspend fun searchSneaker(id: Int): FavoritesEntity?

}