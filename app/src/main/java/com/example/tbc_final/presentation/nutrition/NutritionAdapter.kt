package com.example.tbc_final.presentation.nutrition

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_final.databinding.NutritionItemBinding
import com.example.tbc_final.databinding.StoreItemLayoutBinding
import com.example.tbc_final.domain.model.NutritionModel
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.store.StoreAdapter
import com.example.tbc_final.utils.extensions.setImage

class NutritionAdapter:ListAdapter<NutritionModel.Item,NutritionAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            NutritionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: NutritionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var content: NutritionModel.Item


        fun bind() {
            content = getItem(adapterPosition)
            binding.apply {
                nutrition.text = content.toString().drop(5).dropLast(1)
            }
        }
    }


    class DiffUtilCallback : DiffUtil.ItemCallback<NutritionModel.Item>() {
        override fun areItemsTheSame(
            oldItem: NutritionModel.Item,
            newItem: NutritionModel.Item
        ): Boolean {
            return oldItem.Name == newItem.Name
        }

        override fun areContentsTheSame(
            oldItem: NutritionModel.Item,
            newItem: NutritionModel.Item
        ): Boolean {
            return oldItem == newItem
        }
    }
}