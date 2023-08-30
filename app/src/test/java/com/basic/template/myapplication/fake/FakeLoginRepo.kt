package com.basic.template.myapplication.fake

import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class FakeLoginRepo(private val fakeLoginDataSource: FakeLoginDataSource) : LoginRepo {
    override suspend fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>> {
        return fakeLoginDataSource.fetchLoginApi(loginRequestModel)
    }
}