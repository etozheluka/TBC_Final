package com.example.tbc_final.ui.calculator

import android.graphics.Color
import com.example.tbc_final.MainActivity
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentCalculatorBinding
import com.example.tbc_final.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorFragment :
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {


    override fun start() {
        val activity = requireActivity() as? MainActivity
        activity?.hideNavBar()

        listener()

    }

    private fun listener() {


        binding?.calculateBtn?.setOnClickListener {
            if (binding?.centimeters?.text!!.isEmpty() || binding?.kilograms?.text!!.isEmpty() || calculate().toDouble() < 16.0) {
                Snackbar.make(requireView(), getString(R.string.should), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                binding?.bmi?.text = buildString {
                    append(calculate())
                    append(" BMI")
                }
                bmiStat()
            }

        }

    }


    private fun calculate(): BigDecimal {
        val centimeters = binding?.centimeters?.text.toString().toDouble()
        val kilograms = binding?.kilograms?.text.toString().toDouble()

        val meters = (centimeters / 100)

        return (kilograms / (meters * meters)).toBigDecimal().setScale(1, RoundingMode.DOWN)
    }

    private fun bmiStat(){
        if (calculate().toDouble() > 16.0 && calculate().toDouble() <= 18.5){
            binding?.bmiStat?.apply {
                text = context.getString(R.string.under)
                setTextColor(resources.getColor(R.color.under,null))
            }
        }else if (calculate().toDouble() > 18.5 && calculate().toDouble() <=25.0 ){
            binding?.bmiStat?.apply {
                text = context.getString(R.string.normal)
                setTextColor(resources.getColor(R.color.normal,null))
            }
        }else{
            binding?.bmiStat?.apply {
                text = context.getString(R.string.over)
                setTextColor(resources.getColor(R.color.over,null))
            }
        }
    }
}