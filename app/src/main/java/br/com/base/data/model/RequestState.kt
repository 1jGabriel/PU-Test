package br.com.base.data.model

sealed class RequestState {
    data class Error(val throwable: Throwable) : RequestState()
    object Loading : RequestState()
    object Success : RequestState()
    object Empty : RequestState()
}
