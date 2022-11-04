package com.example.tbc_final.presentation.excercises

import com.example.tbc_final.databinding.ExerciseItemBinding
import com.example.tbc_final.domain.model.BodyExercisesModel
import com.example.tbc_final.presentation.base.BaseAdapter
import com.example.tbc_final.utils.extensions.setImage
import java.util.*


class ExerciseAdapter :
    BaseAdapter<BodyExercisesModel, ExerciseItemBinding>(ExerciseItemBinding::inflate) {

    override fun onBind(binding: ExerciseItemBinding, position: Int) {
        val content = getItem(position)
        binding.apply {
            name.text = content.name?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.ROOT
                ) else it.toString()
            }
            roundedImageView.setImage(content.gifUrl!!)
        }
    }
}