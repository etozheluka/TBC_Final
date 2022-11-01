package com.example.tbc_final.presentation.store

import com.example.tbc_final.databinding.StoreItemLayoutBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.adapter.base.BaseAdapter
import com.example.tbc_final.presentation.excercises.ExerciseAdapter
import com.example.tbc_final.utils.extensions.setImage

class StoreAdapter : BaseAdapter<SneakerModel.Sneaker, StoreItemLayoutBinding>(StoreItemLayoutBinding::inflate) {

    var itemClick: ((SneakerModel.Sneaker) -> Unit)? = null

    override fun onBind(binding: StoreItemLayoutBinding, position: Int) {
        val content = getItem(position)
        val currentItem = currentList[position]
        binding.apply {
            shoeNameTV.text = content.name
            shoesImageView.setImage(content.mainPictureUrl!!)
            this.root.setOnClickListener { itemClick?.invoke(currentItem) }
        }
    }

}