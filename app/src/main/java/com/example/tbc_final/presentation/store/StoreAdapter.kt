package com.example.tbc_final.presentation.store


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_final.databinding.StoreItemLayoutBinding
import com.example.tbc_final.domain.model.Sneaker
import com.example.tbc_final.utils.extensions.setImage

//StoreData.
class StoreAdapter : ListAdapter<Sneaker, StoreAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            StoreItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: StoreItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var content: Sneaker


        fun bind() {
            content = getItem(adapterPosition)
            binding.apply {
                shoeNameTV.text = content.name
                itemCostTV.text = content.retailPriceCents.toString()
                shoesImageView.setImage(content.mainPictureUrl!!)
            }
        }
    }


    class DiffUtilCallback : DiffUtil.ItemCallback<Sneaker>() {
        override fun areItemsTheSame(
            oldItem: Sneaker,
            newItem: Sneaker
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem:Sneaker,
            newItem: Sneaker
        ): Boolean {
            return oldItem == newItem
        }
    }
}