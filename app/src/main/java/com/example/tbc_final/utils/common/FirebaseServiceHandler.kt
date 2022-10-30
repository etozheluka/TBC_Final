package com.example.tbc_final.utils.common


open class FirebaseServiceHandler {
    fun <T> exceptions(e: Throwable): UiState<T> {
        return UiState.Error(Throwable(e.message))
    }

    fun <T> success(data: T): UiState<T> {
        return UiState.Success(data)
    }
}