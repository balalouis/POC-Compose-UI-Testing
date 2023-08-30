package com.basic.template.myapplication.login.data.datasource

import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.BaseApiCall
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginDataSourceImpl(private val apiWebService: ApiWebService) : LoginDataSource,
    BaseApiCall() {
    override suspend fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>> {
        return flow {
            emit(safeApiCall { apiWebService.loginApiCall(loginRequestModel) })
        }.flowOn(Dispatchers.IO)
    }
}
