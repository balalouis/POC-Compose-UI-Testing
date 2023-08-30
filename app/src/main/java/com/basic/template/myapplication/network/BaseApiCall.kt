package com.basic.template.myapplication.network

import retrofit2.Response
abstract class BaseApiCall {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T?> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                return NetworkResult.Success(data)
            }
            return error("${response.code()} ${response.message()}")
        } catch (exp: Exception) {
            return error(exp.message)
        }
    }

    private fun <T> error(message: String?): NetworkResult<T> = NetworkResult.Failure(message)

}