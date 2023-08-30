package com.basic.template.myapplication.fake

import com.basic.template.myapplication.TestDataUtil
import com.basic.template.myapplication.login.data.datasource.LoginDataSource
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLoginDataSource(val isApiSuccess: Boolean = true) : LoginDataSource {
    override suspend fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>> {
        return if (isApiSuccess) {
            flow {
                emit(NetworkResult.Success(TestDataUtil.getLoginSuccessResponse()))
            }
        } else {
            flow {
                emit(NetworkResult.Failure(TestDataUtil.getFailureResponse()))
            }
        }
    }
}