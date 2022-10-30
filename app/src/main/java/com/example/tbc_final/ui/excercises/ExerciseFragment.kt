package com.example.tbc_final.ui.excercises

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.tbc_final.databinding.FragmentExerciseBinding
import com.example.tbc_final.ui.base.BaseFragment
import com.example.tbc_final.utils.BodyPartEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseFragment : BaseFragment<FragmentExerciseBinding>(FragmentExerciseBinding::inflate) {

    private val args: ExerciseFragmentArgs by navArgs()
    private val viewModel: ExerciseViewModel by viewModels()

    private val adapter by lazy {
        ExerciseAdapter()
    }
    override fun onBind() {

        binding?.exerciseRecycler?.adapter = adapter

        when (args.type) {
            BodyPartEnum.CARDIO.bodyPart -> {
                viewModel.getCardio()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.LOWER_LEGS.bodyPart -> {
                viewModel.getLowerLegs()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.UPPER_LEGS.bodyPart -> {
                viewModel.getUpperLegs()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.WAIST.bodyPart -> {
                viewModel.getWaist()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                        viewModel.body.collect{
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
            BodyPartEnum.BACK.bodyPart -> {
                viewModel.getBack()
                viewLifecycleOwner.lifecycleScope.launchWhenResumed {
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