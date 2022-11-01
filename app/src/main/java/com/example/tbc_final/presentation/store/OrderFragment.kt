package com.example.tbc_final.presentation.store

import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbc_final.databinding.FragmentOrderBinding
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.presentation.base.BaseFragment
import com.example.tbc_final.utils.extensions.setImage

class OrderFragment : BaseFragment<FragmentOrderBinding>(FragmentOrderBinding::inflate) {
    private val navigationArgs: OrderFragmentArgs by navArgs()

    override fun onBind() {

        val item = navigationArgs.item
        setData(item)

        binding?.orderBtn?.setOnClickListener{
            findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToOtpFragment())
        }

    }


    private fun setData(item:  SneakerModel.Sneaker) {
        binding.apply {
            this!!.nameTv.text = item.name
            this!!.priceTV.text = item.retailPriceCents.toString()
            item.mainPictureUrl?.let { this!!.shoeIV.setImage(it) }
            this!!.appCompatTextView2.text = HtmlCompat.fromHtml(
                item.storyHtml ?: "",
                HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS
            )
        }
    }
}