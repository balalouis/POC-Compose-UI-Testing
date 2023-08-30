package com.basic.template.myapplication.login.domain.usecases

import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCases @Inject constructor(private var loginRepo: LoginRepo) {
    suspend fun login(loginRequestModel: LoginRequestModel): Flow<NetworkResult<LoginResponseModel?>> =
        loginRepo.login(loginRequestModel)
}