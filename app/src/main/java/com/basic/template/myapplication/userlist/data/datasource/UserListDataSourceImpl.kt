package com.basic.template.myapplication.userlist.data.datasource

import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.model.UserListRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListDataSourceImpl(private val apiWebService: ApiWebService) : UserListDataSource {

    override fun fetchUserList(): Flow<UserListRoot> = flow {
        val loginResponseModel = apiWebService.fetchUserList()
        emit(loginResponseModel)
    }.flowOn(Dispatchers.IO)

}
