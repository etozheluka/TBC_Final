package com.example.tbc_final.presentation.favorites


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbc_final.databinding.FragmentFavoritesBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.presentation.store.StoreFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val viewModel: FavoritesViewModel by viewModels()
    private val favoritesAdapter by lazy {
        FavoritesAdapter()
    }

    override fun onBind() {
        setUpRecycler()
        observers()
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteSneaker.collect {
                favoritesAdapter.submitList(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFavorites()
        }
    }

    private fun setUpRecycler() {
        binding?.clothesRV?.apply {
            adapter = favoritesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }


        favoritesAdapter.itemClick = {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragment2ToOrderFragment(it.toSneakerModelSneaker())
            )

        }


    }
}