package com.example.tbc_final.presentation.store

import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.navArgs
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentOrderBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.extensions.setImage

class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {
    private val navigationArgs: OrderFragmentArgs by navArgs()

    override fun onBind() {

        val item = navigationArgs.item
        setData(item)

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
}