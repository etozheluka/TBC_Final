package com.example.tbc_final.presentation.store

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_final.data.repository.local.FavoritesRepositoryImpl
import com.example.tbc_final.domain.model.SneakerModel
import com.example.tbc_final.mapper.Sneakers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepositoryImpl,
    private val stateHandler: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch {
            searchFavoriteSneaker(
                stateHandler.get<SneakerModel.Sneaker>("item")?.id ?: -1
            )
        }
    }

    private var _favoriteSneakersFlow = MutableSharedFlow<Sneakers?>()
    val favoriteSneakerFlow get() = _favoriteSneakersFlow.asSharedFlow()

    suspend fun saveToFavorite() {
        try {
            favoritesRepository.insertFavoriteSneaker(
                stateHandler.get<SneakerModel.Sneaker>("item")!!.toSneakers()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun removeFromFavorites() {
        try {
            favoritesRepository.deleteFavoriteManga(
                stateHandler.get<SneakerModel.Sneaker>("item")!!.toSneakers()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun searchFavoriteSneaker(id: Int) {
        _favoriteSneakersFlow.emit(
            favoritesRepository.searchFavoriteSneaker(id)
        )
    }

}