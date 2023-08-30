package com.basic.template.myapplication

import com.basic.template.myapplication.model.LoginResponseModel

object TestDataUtil {
    fun getLoginSuccessResponse() = LoginResponseModel(token = "assccfgvvhnjmkn")

    fun getFailureResponse() = "in valid password"
}