package com.basic.template.myapplication.login.data.datasource

import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    suspend fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>>
}