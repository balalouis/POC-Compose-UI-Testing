package com.basic.template.myapplication.userlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.myapplication.model.UserUIState
import com.basic.template.myapplication.userlist.domain.usecases.UserListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(var userListUseCases: UserListUseCases) : ViewModel() {
    private val _uiState = MutableStateFlow<UserUIState>(UserUIState.Success(emptyList()))
    val uiState: StateFlow<UserUIState> = _uiState

    fun fetchUserListApiViaViewModel() {
        viewModelScope.launch {
            userListUseCases.fetchUserList()
                .catch {
                    _uiState.value = UserUIState.Failure(it)
                }
                .collect {
                    _uiState.value = UserUIState.Success(it.userModelList)
                }
        }
    }
}