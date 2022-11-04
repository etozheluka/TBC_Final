package com.example.tbc_final.data.repository.local

import com.example.tbc_final.data.local.localsource.SneakersLocalDataSource
import com.example.tbc_final.domain.repository.local.FavoritesRepository
import com.example.tbc_final.data.mapper.SneakersMapper
import com.example.tbc_final.domain.model.Sneakers
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(
    private val dataSource: SneakersLocalDataSource
) : FavoritesRepository {

    override suspend fun getFavorites(): List<Sneakers> {
        return SneakersMapper.mapFavoriteEntitiesToDomain(dataSource.getFavorites())
    }

    override suspend fun insertFavoriteSneaker(sneakers: Sneakers) {
        return dataSource.insertFavoriteSneaker(SneakersMapper.mapDomainToEntity(sneakers))
    }

    override suspend fun deleteFavoriteManga(sneakers: Sneakers) {
        return dataSource.deleteFavoriteSneaker(SneakersMapper.mapDomainToEntity(sneakers))
    }

    override  suspend fun searchFavoriteSneaker(id: Int): Sneakers? {
        return dataSource.searchFavoriteSneaker(id)?.toSneaker()
    }

}