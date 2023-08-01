package com.basic.template.myapplication.login.domain.usecases

import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCases @Inject constructor(private var loginRepo: LoginRepo) {
    fun login(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel> =
        loginRepo.login(loginRequestModel)
}