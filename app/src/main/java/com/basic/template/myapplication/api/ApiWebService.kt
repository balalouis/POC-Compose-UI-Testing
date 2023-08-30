package com.basic.template.myapplication.api

import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.model.UserListRoot
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiWebService {

    @POST("api/login")
    suspend fun loginApiCall(@Body loginRequestModel: LoginRequestModel): Response<LoginResponseModel>

    @GET("api/users?page=2")
    suspend fun fetchUserList(): UserListRoot

    companion object {
        const val BASE_URL = "https://reqres.in/"
    }
}
