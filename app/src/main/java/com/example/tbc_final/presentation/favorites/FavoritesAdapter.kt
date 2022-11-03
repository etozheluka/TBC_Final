package com.example.tbc_final.presentation.favorites

import com.example.tbc_final.databinding.FavoritesItemLayoutBinding
import com.example.tbc_final.domain.model.Sneakers
import com.example.tbc_final.presentation.base.BaseAdapter
import com.example.tbc_final.utils.extensions.setImage

class FavoritesAdapter: BaseAdapter<Sneakers, FavoritesItemLayoutBinding>(
    FavoritesItemLayoutBinding::inflate)  {
    override fun onBind(binding: FavoritesItemLayoutBinding, position: Int) {
        val content = getItem(position)
        binding.apply {
            shoeNameTV.text = content.name
            shoesImageView.setImage(content.mainPictureUrl!!)

        }
    }
}