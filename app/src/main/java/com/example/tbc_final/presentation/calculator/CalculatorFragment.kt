package com.example.tbc_final.presentation.calculator

import androidx.fragment.app.viewModels
import com.example.tbc_final.R
import com.example.tbc_final.databinding.FragmentCalculatorBinding
import com.example.tbc_final.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

class CalculatorFragment :
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {

    private val viewModel: CalculatorViewModel by viewModels()


    override fun onBind() {

        listener()

    }

    private fun listener() {


        binding?.calculateBtn?.setOnClickListener {

            val centimeters = binding?.centimeters?.text.toString()
            val kilograms = binding?.kilograms?.text.toString()


            if (binding?.centimeters?.text!!.isEmpty() || binding?.kilograms?.text!!.isEmpty() || viewModel.calculate(
                    centimeters.toDouble(),
                    kilograms.toDouble()
                ).toDouble() < 16.0
            ) {
                Snackbar.make(requireView(), getString(R.string.should), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                binding?.bmi?.text = buildString {
                    append(viewModel.calculate(centimeters.toDouble(), kilograms.toDouble()))
                    append(BMI)
                }
                bmiStat(centimeters.toDouble(), kilograms.toDouble())
            }

        }

    }


    private fun bmiStat(centimeters: Double, kilograms: Double) {
        if (viewModel.calculate(centimeters, kilograms)
                .toDouble() > UNDERWEIGHT_MIN && viewModel.calculate(centimeters, kilograms)
                .toDouble() <= UNDERWEIGHT_MAX
        ) {
            binding?.bmiStat?.apply {
                text = context.getString(R.string.under)
                setTextColor(resources.getColor(R.color.under, null))
            }
        } else if (viewModel.calculate(centimeters, kilograms)
                .toDouble() > UNDERWEIGHT_MAX && viewModel.calculate(centimeters, kilograms)
                .toDouble() <= NORMAL_MAX
        ) {
            binding?.bmiStat?.apply {
                text = context.getString(R.string.normal)
                setTextColor(resources.getColor(R.color.normal, null))
            }
        } else {
            binding?.bmiStat?.apply {
                text = context.getString(R.string.over)
                setTextColor(resources.getColor(R.color.over, null))
            }
        }
    }

    companion object {
        const val BMI = " BMI"
        const val UNDERWEIGHT_MIN = 16.0
        const val UNDERWEIGHT_MAX = 18.5
        const val NORMAL_MAX = 25.0

    }
}