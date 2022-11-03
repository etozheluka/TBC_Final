package com.example.tbc_final.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.data.repository.local.FavoritesRepositoryImpl
import com.example.tbc_final.domain.model.Sneakers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepositoryImpl
) : ViewModel() {

    private val _favoriteSneakers = MutableSharedFlow<List<Sneakers>>()
    val favoriteSneaker get() = _favoriteSneakers.asSharedFlow()


    fun getFavorites() {
        viewModelScope.launch {
            val response = favoritesRepository.getFavorites()
            _favoriteSneakers.emit(response)

        }
    }
}