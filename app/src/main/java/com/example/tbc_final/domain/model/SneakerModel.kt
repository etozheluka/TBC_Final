package com.example.tbc_final.domain.model

import android.os.Parcelable
import com.example.tbc_final.presentation.adapter.base.BaseDiff
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class SneakerModel(
    val sneakers: List<Sneaker>?
) : Serializable {
    @Parcelize
    data class Sneaker(
        val id: Int?,
        @Json(name = "main_picture_url")
        val mainPictureUrl: String?,
        val name: String?,
        @Json(name = "retail_price_cents")
        val retailPriceCents: Int?,
        @Json(name = "story_html")
        val storyHtml: String?,
        val category: List<String?>?,
        var isFavorite: Boolean = false,

        ) : BaseDiff<Sneaker>(), Serializable,Parcelable {

        fun toSneakers() = Sneakers(
            id = id,
            mainPictureUrl = mainPictureUrl,
            name = name,
            isFavorite = isFavorite
        )

        override val inner: Sneaker
            get() = this

        override val uniqueValue: Any
            get() = id ?: ""

        override fun compareTo(other: Any?): Boolean {
            return other is Sneaker && this == other
        }
    }
}