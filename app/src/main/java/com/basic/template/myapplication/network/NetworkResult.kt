package com.basic.template.myapplication.network

sealed class NetworkResult<T>(val data: Any? = null, val message: String? = null) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Failure<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
}