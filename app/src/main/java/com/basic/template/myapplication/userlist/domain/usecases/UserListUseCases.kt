package com.basic.template.myapplication.userlist.domain.usecases

import com.basic.template.myapplication.userlist.domain.repo.UserListRepo
import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCases @Inject constructor(private var userListRepo: UserListRepo) {
    fun fetchUserList(): Flow<NetworkResult<UserListRoot?>> =
        userListRepo.fetchUserList()
}