package com.example.tbc_final.presentation.store

import com.example.tbc_final.databinding.HorizontalStoreItemBinding
import com.example.tbc_final.databinding.StoreItemLayoutBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.adapter.base.BaseAdapter
import com.example.tbc_final.utils.extensions.setImage

class HorizontalAdapter:
    BaseAdapter<SneakerModel.Sneaker, HorizontalStoreItemBinding>(HorizontalStoreItemBinding::inflate) {
    var itemClick: ((SneakerModel.Sneaker) -> Unit)? = null
    override fun onBind(binding: HorizontalStoreItemBinding, position: Int) {
        val content = getItem(position)



        binding.apply {
            nameFeatured.text = content.name.toString()
            imageFeatured.setImage(content.mainPictureUrl.toString())
            root.setOnClickListener { itemClick?.invoke(content) }
        }

    }
}