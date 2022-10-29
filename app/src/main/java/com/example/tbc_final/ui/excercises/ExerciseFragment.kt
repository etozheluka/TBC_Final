package com.example.tbc_final.ui.excercises

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentBodyPartBinding
import com.example.tbc_final.databinding.FragmentExerciseBinding
import com.example.tbc_final.ui.base.BaseFragment
import com.example.tbc_final.utils.BodyPartEnum
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExerciseFragment : BaseFragment<FragmentExerciseBinding>(FragmentExerciseBinding::inflate) {

    private val args: ExerciseFragmentArgs by navArgs()
    private val viewModel: BodyPartViewModel by viewModels()

    private val adapter by lazy {
        ExerciseAdapter()
    }
    override fun start() {

        binding?.exerciseRecycler?.adapter = adapter

        when (args.type) {
            BodyPartEnum.CARDIO.bodyPart -> {
                viewModel.getCardio()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.LOWER_LEGS.bodyPart -> {
                viewModel.getLowerLegs()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.UPPER_LEGS.bodyPart -> {
                viewModel.getUpperLegs()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.WAIST.bodyPart -> {
                viewModel.getWaist()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.BACK.bodyPart -> {
                viewModel.getBack()
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }
}