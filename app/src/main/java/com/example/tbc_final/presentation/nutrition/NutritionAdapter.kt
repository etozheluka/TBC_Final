package com.example.tbc_final.presentation.nutrition

import com.example.tbc_final.databinding.NutritionItemBinding
import com.example.tbc_final.domain.model.NutritionModel
import com.example.tbc_final.presentation.base.BaseAdapter


class NutritionAdapter :
    BaseAdapter<NutritionModel.Item, NutritionItemBinding>(NutritionItemBinding::inflate) {


    override fun onBind(binding: NutritionItemBinding, position: Int) {
        val content = getItem(position)
        binding.apply {
            nutrition.text = content.toString().drop(5).dropLast(1)
        }
    }
}