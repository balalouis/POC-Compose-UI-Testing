package com.basic.template.myapplication.userlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.myapplication.model.UserListRoot
import com.basic.template.myapplication.network.NetworkResult
import com.basic.template.myapplication.userlist.domain.usecases.UserListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(var userListUseCases: UserListUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow<NetworkResult<UserListRoot?>>(
        NetworkResult.Success(
            UserListRoot()
        )
    )
    val uiState: StateFlow<NetworkResult<UserListRoot?>> = _uiState

    fun fetchUserListApiViaViewModel() {
        viewModelScope.launch {
            userListUseCases.fetchUserList()
                .collect {
                    _uiState.value = it
                }
        }
    }
}