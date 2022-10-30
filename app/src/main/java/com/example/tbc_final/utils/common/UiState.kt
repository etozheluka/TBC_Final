package com.example.tbc_final.utils.common

sealed class UiState<T>( val isLoading:Boolean){
    class Success<T> (val response:T): UiState<T>(false)
    class Error<T>(val errorResponse: Throwable): UiState<T>(false)
    class InProcess<T> (): UiState<T>(true)
}