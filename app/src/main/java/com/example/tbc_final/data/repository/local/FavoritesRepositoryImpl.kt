package com.example.tbc_final.data.repository.local

import com.example.tbc_final.data.local.localsource.SneakersLocalDataSource
import com.example.tbc_final.domain.repository.local.FavoritesRepository
import com.example.tbc_final.mapper.DataMapper
import com.example.tbc_final.mapper.Sneakers
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(
    private val dataSource: SneakersLocalDataSource
) : FavoritesRepository {

    override suspend fun getFavorites(): List<Sneakers> {
        return DataMapper.mapFavoriteEntitiesToDomain(dataSource.getFavorites())
    }

    override suspend fun insertFavoriteSneaker(sneakers: Sneakers) {
        return dataSource.insertFavoriteSneaker(DataMapper.mapDomainToEntity(sneakers))
    }

    override suspend fun deleteFavoriteManga(sneakers: Sneakers) {
        return dataSource.deleteFavoriteSneaker(DataMapper.mapDomainToEntity(sneakers))
    }

    override  suspend fun searchFavoriteSneaker(id: Int): Sneakers? {
        return dataSource.searchFavoriteSneaker(id)?.toSneaker()
    }

}