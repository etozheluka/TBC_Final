package com.example.tbc_final.ui.excercises

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbc_final.databinding.ExerciseItemBinding
import com.example.tbc_final.domain.model.BodyExercisesModel
import com.example.tbc_final.utils.extensions.setImage
import java.util.*

class ExerciseAdapter: ListAdapter<BodyExercisesModel, ExerciseAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var content: BodyExercisesModel


        fun bind() {
            content = getItem(adapterPosition)

            binding.apply {
                exName.text = content.name?.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                }
                gifImage.setImage(content.gifUrl!!)
                exEquipment.text = content.equipment
                exTarget.text = content.target
            }
        }

    }


    class DiffUtilCallback : DiffUtil.ItemCallback<BodyExercisesModel>() {
        override fun areItemsTheSame(
            oldItem: BodyExercisesModel,
            newItem: BodyExercisesModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BodyExercisesModel,
            newItem: BodyExercisesModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}