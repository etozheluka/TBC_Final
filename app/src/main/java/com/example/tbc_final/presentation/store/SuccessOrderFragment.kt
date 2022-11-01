package com.example.tbc_final.presentation.store

import androidx.navigation.fragment.findNavController
import com.example.tbc_final.databinding.FragmentOrderFinishBinding
import com.example.tbc_final.presentation.base.BaseFragment

class OrderFinishFragment :
    BaseFragment<FragmentOrderFinishBinding>(FragmentOrderFinishBinding::inflate) {
    override fun onBind() {
        listener()
    }

    private fun listener() {
        binding?.backToHomeBtn?.setOnClickListener {
            findNavController().navigate(OrderFinishFragmentDirections.actionOrderFinishFragmentToHomeFragment())
        }
    }

}