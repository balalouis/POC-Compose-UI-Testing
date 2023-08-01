package com.basic.template.myapplication.login.data.repo

import com.basic.template.myapplication.login.data.datasource.LoginDataSource
import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

class LoginRepoImpl(private var loginDataSource: LoginDataSource): LoginRepo {
    override fun login(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel> =
        loginDataSource.fetchLoginApi(loginRequestModel)
}