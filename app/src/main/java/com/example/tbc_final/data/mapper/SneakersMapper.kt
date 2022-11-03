package com.example.tbc_final.data.mapper

import com.example.tbc_final.data.local.entity.FavoritesEntity
import com.example.tbc_final.domain.model.Sneakers

object SneakersMapper {


    fun mapFavoriteEntitiesToDomain(input: List<FavoritesEntity>): List<Sneakers> {
        return input.map {
            Sneakers(
                id = it.id,
                name = it.name,
                mainPictureUrl = it.mainPictureUrl,
                isFavorite = it.isFavorite
            )
        }
    }


    fun mapDomainToEntity(input: Sneakers) = FavoritesEntity(
        id = input.id,
        isFavorite = input.isFavorite,
        name = input.name,
        mainPictureUrl = input.mainPictureUrl

    )

}