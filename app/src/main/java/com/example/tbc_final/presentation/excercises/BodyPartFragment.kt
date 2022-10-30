package com.example.tbc_final.presentation.excercises

import androidx.navigation.fragment.findNavController
import com.example.tbc_final.databinding.FragmentBodyPartBinding

import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.BodyPartEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BodyPartFragment : BaseFragment<FragmentBodyPartBinding>(FragmentBodyPartBinding::inflate) {


    private val adapter by lazy {
        BodyPartAdapter()
    }

    override fun onBind() {


        binding?.bodyPartRecycler?.adapter = adapter


        adapter.onClick = {
            setUpApi(it)
        }


    }

    private fun setUpApi(bodyPart: BodyPartEnum) {

        when(bodyPart){
            BodyPartEnum.CARDIO -> navigate(BodyPartEnum.CARDIO.bodyPart)
            BodyPartEnum.LOWER_LEGS -> navigate(BodyPartEnum.LOWER_LEGS.bodyPart)
            BodyPartEnum.UPPER_LEGS -> navigate(BodyPartEnum.UPPER_LEGS.bodyPart)
            BodyPartEnum.WAIST -> navigate(BodyPartEnum.WAIST.bodyPart)
            BodyPartEnum.BACK -> navigate(BodyPartEnum.BACK.bodyPart)
        }

    }

    private fun navigate(bodyPart: String){
        findNavController().navigate(BodyPartFragmentDirections.actionLeaderFragmentToExerciseFragment(bodyPart))
    }
}
