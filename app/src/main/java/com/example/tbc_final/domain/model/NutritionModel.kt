package com.example.tbc_final.domain.model

import com.squareup.moshi.Json

data class NutritionModel(
    val items: List<Item?>?
) {
    data class Item(
        @Json(name = "sugar_g")
        val SugarGrams: Double?,
        @Json(name = "fiber_g")
        val FiberGrams: Double?,
        @Json(name = "serving_size_g")
        val ServingSizeGrams: Double?,
        @Json(name = "sodium_mg")
        val SodiumMGrams: Int?,
        @Json(name = "name")
        val Name: String?,
        @Json(name = "potassium_mg")
        val PotassiumMGrams: Int?,
        @Json(name = "fat_saturated_g")
        val FatSaturatedGrams: Double?,
        @Json(name = "fat_total_g")
        val FatTotalGrams: Double?,
        @Json(name = "calories")
        val Calories: Double?,
        @Json(name = "cholesterol_mg")
        val CholesterolMGrams: Int?,
        @Json(name = "protein_g")
        val ProteinGrams: Double?,
        @Json(name = "carbohydrates_total_g")
        val CarbohydratesTotalGrams: Double?
    )
}