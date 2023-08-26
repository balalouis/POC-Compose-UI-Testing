package com.basic.template.myapplication.fake

import com.basic.template.myapplication.TestDataUtil
import com.basic.template.myapplication.login.data.datasource.LoginDataSource
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLoginDataSource : LoginDataSource {
    override fun fetchLoginApi(loginRequestModel: LoginRequestModel): Flow<LoginResponseModel> {
        return flow { emit(TestDataUtil.getLoginResponseModel()) }
    }
}