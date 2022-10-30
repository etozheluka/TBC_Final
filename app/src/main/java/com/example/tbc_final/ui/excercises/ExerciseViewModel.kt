package com.example.tbc_final.ui.excercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.utils.common.Resource
import com.example.tbc_final.domain.model.BodyExercisesModel
import com.example.tbc_final.domain.use_case.exercises.GetBodyPartsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getBodyPartUseCase: GetBodyPartsUseCase
):ViewModel(){


    private val _body = MutableStateFlow(Resource<List<BodyExercisesModel>>())
    val body = _body.asStateFlow()

    fun getCardio(){
        viewModelScope.launch {
            val response = getBodyPartUseCase.getCardio()
            response.collect{
                _body.value =  it
            }
        }
    }
    fun getLowerLegs(){
        viewModelScope.launch {
            val response = getBodyPartUseCase.getLowerLegs()
            response.collect{
                _body.value =  it
            }
        }
    }
    fun getUpperLegs(){
        viewModelScope.launch {
            val response = getBodyPartUseCase.getUpperLegs()
            response.collect{
                _body.value =  it
            }
        }
    }
    fun getWaist(){
        viewModelScope.launch {
            val response = getBodyPartUseCase.getWaist()
            response.collect{
                _body.value =  it
            }
        }
    }
    fun getBack(){
        viewModelScope.launch {
            val response = getBodyPartUseCase.getBack()
            response.collect{
                _body.value =  it
            }
        }
    }
}