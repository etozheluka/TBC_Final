package com.example.tbc_final.presentation.store

import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentOrderBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.extensions.setImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {
    private val navigationArgs: OrderFragmentArgs by navArgs()
    private val viewModel: OrderViewModel by viewModels()
    private var isFavorite = false

    override fun onBind() {

        val item = navigationArgs.item
        setData(item)
        onClickListeners()
        initObserver()

    }

    private fun setData(item: SneakerModel.Sneaker) {
        binding?.apply {
            nameTv.text = item.name
            priceTV.text = buildString {
                append((item.retailPriceCents?.div(100)).toString())
                append("$")
            }
            item.mainPictureUrl?.let { shoeIV.setImage(it) }
            appCompatTextView2.text = HtmlCompat.fromHtml(
                item.storyHtml ?: getString(R.string.lorem),
                HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS
            )
        }
    }

    private fun onClickListeners() {
        binding?.ivFavorite?.setOnClickListener {
            if (isFavorite) {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.removeFromFavorites()
                    binding?.ivFavorite?.setImageResource(R.drawable.ic_fav)
                }
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.saveToFavorite()
                    binding?.ivFavorite?.setImageResource(R.drawable.ic_favfull)
                }
            }
        }
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteSneakerFlow.collect { sneaker ->
                sneaker?.let {
                    isFavorite = true
                    binding?.ivFavorite?.setImageResource(R.drawable.ic_favfull)
                } ?: run {
                    isFavorite = false
                    binding?.ivFavorite?.setImageResource(R.drawable.ic_fav)
                }
            }
        }
    }
}
