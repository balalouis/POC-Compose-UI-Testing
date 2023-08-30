package com.basic.template.myapplication.login.domain.repo

import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    suspend fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>>
}