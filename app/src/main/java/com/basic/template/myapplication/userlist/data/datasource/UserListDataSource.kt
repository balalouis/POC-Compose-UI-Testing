package com.basic.template.myapplication.userlist.data.datasource

import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserListDataSource {
    fun fetchUserList(): Flow<NetworkResult<UserListRoot?>>
}