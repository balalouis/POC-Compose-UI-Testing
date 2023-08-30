package com.basic.template.myapplication.model

data class LoginRequestModel(val email: String, val password: String)

data class LoginResponseModel(val token: String?)
