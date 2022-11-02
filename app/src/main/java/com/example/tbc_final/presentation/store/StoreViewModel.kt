package com.example.tbc_final.presentation.store

import androidx.lifecycle.ViewModel
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.domain.repository.remote.StoreRepository
import com.example.tbc_final.utils.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository): ViewModel() {

    private val _storeFlow = MutableSharedFlow<Resource<SneakerModel>>()
    val storeFlow get() = _storeFlow.asSharedFlow()



    suspend fun getItems(){
        storeRepository.getStoreItems().collect{
            _storeFlow.emit(it)
        }
    }

    fun filter(text: String, data: List<SneakerModel.Sneaker?>?,adapter: StoreAdapter) {


        val filteredStore = ArrayList<SneakerModel.Sneaker?>()

        data?.filterTo(filteredStore) { item ->
            item?.name?.lowercase()?.contains(text.lowercase()) ?: true
        }

        adapter.submitList(filteredStore)


    }
//    fun filterHorizontal( data: List<SneakerModel.Sneaker?>?,adapter: HorizontalAdapter) {
//
//
//        val filteredStore = ArrayList<SneakerModel.Sneaker?>()
//
//        data?.filterTo(filteredStore) { item ->
//            item?.category?.contains("running") ?: true
//        }
//
//        adapter.submitList(filteredStore)
//

    }