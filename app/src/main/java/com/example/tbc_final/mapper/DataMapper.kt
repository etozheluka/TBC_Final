package com.example.tbc_final.mapper

import com.example.tbc_final.data.local.entity.FavoritesEntity
import com.example.tbc_final.domain.model.SneakerModel

object DataMapper {
    
    fun mapFavoriteEntitiesToDomain(input: List<FavoritesEntity>): List<Sneakers> {
        val sneakers = mutableListOf<Sneakers>()
        input.forEach {
            val manga = Sneakers(
                id = it.id,
                name = it.name,
                mainPictureUrl = it.mainPictureUrl,
                isFavorite = it.isFavorite
            )
            sneakers.add(manga)
        }
        return sneakers
    }


    fun mapDomainToEntity(input: Sneakers) = FavoritesEntity(
        id = input.id,
        isFavorite = input.isFavorite,
        name = input.name,
        mainPictureUrl = input.mainPictureUrl

    )

}