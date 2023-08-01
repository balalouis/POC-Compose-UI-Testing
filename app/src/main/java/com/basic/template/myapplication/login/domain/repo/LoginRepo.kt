package com.basic.template.myapplication.login.domain.repo

import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    fun login(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel>
}