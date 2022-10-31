package com.example.tbc_final.domain.model

import com.example.tbc_final.presentation.adapter.base.BaseDiff
import com.squareup.moshi.Json
import java.io.Serializable

data class SneakerModel(
    val sneakers: List<Sneaker?>?
): Serializable {
    data class Sneaker(
        @Json(name = "box_condition")
        val boxCondition: String?,
        @Json(name = "brand_name")
        val brandName: String?,
        val category: List<String?>?,
        @Json(name = "collection_slugs")
        val collectionSlugs: List<String?>?,
        val color: String?,
        val designer: String?,
        val details: String?,
        @Json(name = "grid_picture_url")
        val gridPictureUrl: String?,
        @Json(name = "has_picture")
        val hasPicture: Boolean?,
        @Json(name = "has_stock")
        val hasStock: Boolean?,
        val id: Int?,
        val keywords: List<String?>?,
        @Json(name = "main_picture_url")
        val mainPictureUrl: String?,
        val midsole: String?,
        val name: String?,
        val nickname: String?,
        @Json(name = "original_picture_url")
        val originalPictureUrl: String?,
        @Json(name = "product_template_id")
        val productTemplateId: Int?,
        @Json(name = "release_date")
        val releaseDate: String?,
        @Json(name = "release_date_unix")
        val releaseDateUnix: Int?,
        @Json(name = "release_year")
        val releaseYear: Int?,
        @Json(name = "retail_price_cents")
        val retailPriceCents: Int?,
        @Json(name = "shoe_condition")
        val shoeCondition: String?,
        val silhouette: String?,
        @Json(name = "size_range")
        val sizeRange: List<Double?>?,
        val sku: String?,
        val slug: String?,
        val status: String?,
        @Json(name = "story_html")
        val storyHtml: String?,
        @Json(name = "upper_material")
        val upperMaterial: String?
    ) : BaseDiff<Sneaker>(), Serializable {

        override val inner: Sneaker
            get() = this

        override val uniqueValue: Any
            get() = id ?: ""

        override fun compareTo(other: Any?): Boolean {
            return other is Sneaker && this == other
        }
    }
}