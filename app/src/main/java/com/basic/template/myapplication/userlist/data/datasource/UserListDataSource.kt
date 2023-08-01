package com.basic.template.myapplication.userlist.data.datasource

import com.basic.template.myapplication.model.UserListRoot
import kotlinx.coroutines.flow.Flow

interface UserListDataSource {
    fun fetchUserList(): Flow<UserListRoot>
}