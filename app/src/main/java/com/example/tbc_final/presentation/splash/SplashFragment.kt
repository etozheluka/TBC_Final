package com.example.tbc_final.presentation.splash


import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tbc_final.databinding.FragmentSplashBinding
import com.example.tbc_final.presentation.base.BaseFragment
import kotlinx.coroutines.delay


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onBind() {
        lifecycleScope.launchWhenCreated {
            delay(2000)
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToStartFragment())
        }
    }
}