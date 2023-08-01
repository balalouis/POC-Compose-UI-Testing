package com.basic.template.myapplication.userlist.domain.repo

import com.basic.template.myapplication.model.UserListRoot
import kotlinx.coroutines.flow.Flow

interface UserListRepo {
    fun fetchUserList(): Flow<UserListRoot>
}