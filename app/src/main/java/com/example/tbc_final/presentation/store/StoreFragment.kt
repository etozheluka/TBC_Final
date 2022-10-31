package com.example.tbc_final.presentation.store


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_final.databinding.FragmentStoreBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.common.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::inflate) {

    private val storeViewModel: StoreViewModel by viewModels()
    private val storeAdapter by lazy {
        StoreAdapter()
    }

    override fun onBind() {
        observer()
        setUpRecycler()
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            storeViewModel.storeFlow.collect {
                storeAdapter.submitList(it.data?.sneakers)
            }
        }
    }

    private fun setUpRecycler() {

        binding?.clothesRV?.apply {
            adapter = storeAdapter
            layoutManager = GridLayoutManager(context,2)
        }
        lifecycleScope.launch {
            storeViewModel.getItems()
        }
    }
}
