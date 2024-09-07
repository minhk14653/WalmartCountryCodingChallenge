package com.example.walmartcountrycodingchallenge.util

sealed class State {
    data class LOADING(val loading: Boolean = true) : State()
    class SUCCESS<T>(val country: T, val isLoading: Boolean = false) : State()
    class ERROR(val exception: Throwable, val isLoading: Boolean = false) : State()
}

class NullResponseException(message: String = "Error happened here") : Exception(message)
class FailureResponse(message: String) : Exception(message)
