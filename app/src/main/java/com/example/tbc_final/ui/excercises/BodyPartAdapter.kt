package com.example.tbc_final.ui.excercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_final.databinding.BodyPartRecyclerItemBinding
import com.example.tbc_final.utils.BodyPartEnum

typealias onClick = (content: BodyPartEnum) -> Unit

class BodyPartAdapter:RecyclerView.Adapter<BodyPartAdapter.ViewHolder>() {

    lateinit var onClick: onClick

    private val content = BodyPartEnum.values().toList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BodyPartAdapter.ViewHolder = ViewHolder(
        BodyPartRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: BodyPartAdapter.ViewHolder, position: Int) {


        holder.bind()

    }

    override fun getItemCount(): Int {
        return content.size
    }

    inner class ViewHolder(private val binding: BodyPartRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var currentItem: BodyPartEnum
        fun bind() {
            currentItem = content[adapterPosition]
            binding.apply {
                bodyPart.text = currentItem.bodyPart
                root.setOnClickListener {
                    onClick(
                        currentItem
                    )
                }
            }

        }
    }

}