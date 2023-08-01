package com.basic.template.myapplication.login.data.datasource

import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.model.LoginRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginDataSourceImpl(private val apiWebService: ApiWebService) : LoginDataSource {
    override fun fetchLoginApi(loginRequestModel: LoginRequestModel) = flow {
        val loginResponseModel = apiWebService.loginApiCall(loginRequestModel)
        emit(loginResponseModel)
    }.flowOn(Dispatchers.IO)
}
