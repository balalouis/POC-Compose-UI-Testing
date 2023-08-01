package com.basic.template.myapplication.login.data.datasource

import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel>
}