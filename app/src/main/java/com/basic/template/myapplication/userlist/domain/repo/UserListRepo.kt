package com.basic.template.myapplication.userlist.domain.repo

import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserListRepo {
    fun fetchUserList(): Flow<NetworkResult<UserListRoot?>>
}