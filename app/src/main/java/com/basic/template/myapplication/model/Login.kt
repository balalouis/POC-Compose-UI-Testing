package com.basic.template.myapplication.model

data class LoginRequestModel(val email: String, val password: String)

data class LoginResponseModel(val token: String?)

sealed class LoginUiState {
    object Loading : LoginUiState()
    data class Success(var loginResponseModel: LoginResponseModel?) : LoginUiState()
    data class Error(var exception: Throwable) : LoginUiState()
}