package com.example.tbc_final.presentation.calculator

import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorViewModel:ViewModel() {


    fun calculate(centimeters:Double,kilograms:Double):BigDecimal{

        val meters = (centimeters / 100)
        return (kilograms / (meters * meters)).toBigDecimal().setScale(1, RoundingMode.DOWN)

    }
}