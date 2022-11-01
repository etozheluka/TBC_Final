package com.example.tbc_final.presentation.nutrition

import androidx.lifecycle.ViewModelProvider
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
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentCalculatorBinding
import com.example.tbc_final.databinding.FragmentNutritionBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.presentation.calculator.CalculatorViewModel
import com.example.tbc_final.presentation.store.StoreAdapter
import com.example.tbc_final.utils.common.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.g

@AndroidEntryPoint
class NutritionFragment : BaseFragment<FragmentNutritionBinding>(FragmentNutritionBinding::inflate) {

    private val viewModel: NutritionViewModel by viewModels()

    private val nutritionAdapter by lazy {
        NutritionAdapter()
    }

    override fun onBind() {
        setUpAdapter()
        listener()

    }

    private fun setUpAdapter(){
        binding?.nutritionRecycler?.adapter = nutritionAdapter
    }
    private fun listener(){
        binding?.calculateBtn?.setOnClickListener {
            observer()
        }
    }

    private fun observer(){
        val product = binding?.productEditText?.text.toString()
        val grams = binding?.gramsET?.text.toString()
        val query = "${grams}g $product"

        viewModel.getNutrition(query)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.nutritionFlow.collect {
                   if (it.status == Resource.Status.LOADING){
                       binding?.progressBar?.visibility = View.VISIBLE
                   }else{
                       binding?.progressBar?.visibility = View.GONE
                       nutritionAdapter.submitList(it.data?.items)
                   }
                }
            }
        }
    }
}