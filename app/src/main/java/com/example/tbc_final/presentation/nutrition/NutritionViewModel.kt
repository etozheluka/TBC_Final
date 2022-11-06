package com.example.tbc_final.presentation.nutrition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.domain.model.NutritionModel
import com.example.tbc_final.domain.use_case.nutrition.GetNutritionUseCase
import com.example.tbc_final.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class NutritionViewModel @Inject constructor(private val nutritionUseCase: GetNutritionUseCase) : ViewModel() {

    private val _nutritionFlow = MutableStateFlow(Resource<NutritionModel>())
    val nutritionFlow get() = _nutritionFlow.asSharedFlow()

    fun getNutrition(query: String){
        viewModelScope.launch {
            nutritionUseCase.invoke(query).collect{
                _nutritionFlow.value = it
            }
        }

    }
}