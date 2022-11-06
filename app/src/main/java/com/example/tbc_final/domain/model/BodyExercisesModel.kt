package com.example.tbc_final.domain.model

import com.example.tbc_final.presentation.base.BaseDiff

data class BodyExercisesModel(
    val bodyPart: String?,
    val equipment: String?,
    val gifUrl: String?,
    val id: String?,
    val name: String?,
    val target: String?
): BaseDiff<BodyExercisesModel>() {
    override val inner: BodyExercisesModel
        get() = this
    override val uniqueValue: Any
        get() = id ?: ""

    override fun compareTo(other: Any?): Boolean {
        return other is BodyExercisesModel && this == other
    }
}