package com.basic.template.myapplication.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.myapplication.login.domain.usecases.LoginUseCases
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var loginUseCases: LoginUseCases) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Success(LoginResponseModel("")))
    val uiState: StateFlow<LoginUiState> = _uiState

    fun loginApiViewModel(loginRequestModel: LoginRequestModel) {
        viewModelScope.launch {
            loginUseCases.login(loginRequestModel)
                .catch {
                    _uiState.value = LoginUiState.Error(it)
                }
                .collect {
                    _uiState.value = LoginUiState.Success(it)
                }
        }
    }

}