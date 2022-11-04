package com.example.tbc_final.presentation.store


import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_final.databinding.FragmentStoreBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.common.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::inflate) {

    private val storeViewModel: StoreViewModel by viewModels()
    private val storeAdapter by lazy {
        StoreAdapter()
    }
    private val horizontalAdapter by lazy {
        HorizontalAdapter()
    }

    override fun onBind() {
        observer()
        setUpRecycler()

    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            storeViewModel.storeFlow.collect {
                when(it.status){
                    Resource.Status.LOADING -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding?.progressBar?.visibility = View.GONE
                        storeViewModel.filterHorizontal(it.data?.sneakers, horizontalAdapter)
                        storeAdapter.submitList(it.data?.sneakers)
                        setUpSearchBar(it.data?.sneakers)
                        binding?.searchEditText?.text?.clear()
                    }
                    else -> {}
                }

            }
        }
    }

    private fun setUpRecycler() {
        binding?.horizontalRV?.adapter = horizontalAdapter
        binding?.clothesRV?.apply {
            adapter = storeAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        lifecycleScope.launch {
            storeViewModel.getItems()
        }

        storeAdapter.itemClick = {
            findNavController().navigate(
                StoreFragmentDirections.actionStoreFragmentToOrderFragment(
                    it
                )
            )
        }
        horizontalAdapter.itemClick = {
            findNavController().navigate(
                StoreFragmentDirections.actionStoreFragmentToOrderFragment(
                    it
                )
            )
        }


    }


    private fun setUpSearchBar(data: List<SneakerModel.Sneaker?>?) {

        binding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                storeViewModel.filter(p0.toString(), data, storeAdapter)
            }


        })

    }


}

