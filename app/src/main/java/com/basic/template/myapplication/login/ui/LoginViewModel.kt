package com.basic.template.myapplication.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.basic.template.myapplication.login.domain.usecases.LoginUseCases
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginResponseModel
import com.basic.template.myapplication.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private var loginUseCases: LoginUseCases) : ViewModel() {

    private val _uiState =
        MutableStateFlow<NetworkResult<LoginResponseModel?>>(
            NetworkResult.Success(
                LoginResponseModel("")
            )
        )
    val uiState: StateFlow<NetworkResult<LoginResponseModel?>> = _uiState

    private val defaultLoginRequestModel =
        LoginRequestModel(email = "eve.holt@reqres.in", password = "cityslicka")

    fun loginApiViewModel(loginRequestModel: LoginRequestModel = defaultLoginRequestModel) {
        _uiState.value = NetworkResult.Loading()
        viewModelScope.launch {
            loginUseCases.login(loginRequestModel)
                .collect {
                    _uiState.value = it
                }
        }
    }
}