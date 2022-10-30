package com.example.tbc_final.presentation.store

import androidx.lifecycle.ViewModel
import com.example.tbc_final.domain.model.Sneaker
import com.example.tbc_final.domain.repository.remote.StoreRepository
import com.example.tbc_final.utils.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository): ViewModel() {

    private val _storeFlow = MutableSharedFlow<UiState<List<Sneaker>>>()
    val storeFlow get() = _storeFlow.asSharedFlow()

    suspend fun getItems(){
        storeRepository.getStoreItems().collect{
            _storeFlow.emit(it)
        }
    }
}