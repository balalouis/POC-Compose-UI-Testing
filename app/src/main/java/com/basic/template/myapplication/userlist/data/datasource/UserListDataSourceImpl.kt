package com.basic.template.myapplication.userlist.data.datasource

import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.BaseApiCall
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserListDataSourceImpl(private val apiWebService: ApiWebService) : UserListDataSource,
    BaseApiCall() {

    override fun fetchUserList(): Flow<NetworkResult<UserListRoot?>> = flow {
        emit(safeApiCall { apiWebService.fetchUserList() })
    }.flowOn(Dispatchers.IO)

}
