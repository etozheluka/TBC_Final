package com.example.tbc_final.ui.home

import androidx.lifecycle.ViewModel
import com.example.tbc_final.domain.use_case.preferences.GetStepUseCase
import com.example.tbc_final.domain.use_case.preferences.PutStepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val putStepUseCase: PutStepUseCase,
        private val getStepUseCase: GetStepUseCase
): ViewModel() {

    suspend fun putStep(
        step:String
    ){
        putStepUseCase.invoke(step)
    }

    suspend fun getStep():Result<String> = getStepUseCase.invoke()
}