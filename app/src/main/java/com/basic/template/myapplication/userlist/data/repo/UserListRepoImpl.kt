package com.basic.template.myapplication.userlist.data.repo

import com.basic.template.myapplication.userlist.data.datasource.UserListDataSource
import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import com.basic.template.myapplication.userlist.domain.repo.UserListRepo
import kotlinx.coroutines.flow.Flow

class UserListRepoImpl(private var userListDataSource: UserListDataSource) : UserListRepo {
    override fun fetchUserList(): Flow<NetworkResult<UserListRoot?>> = userListDataSource.fetchUserList()
}