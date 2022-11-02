package com.example.tbc_final.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tbc_final.mapper.Sneakers

@Entity(tableName = "favorite_sneakers")
data class FavoritesEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "main_picture_url")
    val mainPictureUrl: String?,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false

) {

    fun toSneaker() = Sneakers(
        id = id,
        name = name,
        mainPictureUrl = mainPictureUrl,
        isFavorite = isFavorite
    )

}