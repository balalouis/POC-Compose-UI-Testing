package com.basic.template.myapplication.login.ui

import com.basic.template.myapplication.CoroutineTestRule
import com.basic.template.myapplication.TestDataUtil
import com.basic.template.myapplication.fake.FakeLoginDataSource
import com.basic.template.myapplication.fake.FakeLoginRepo
import com.basic.template.myapplication.login.domain.usecases.LoginUseCases
import com.basic.template.myapplication.model.LoginUiState
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var loginViewModel: LoginViewModel

    @Test
    fun testDefaultCall() {

        val fakeLoginDataSource = FakeLoginDataSource()
        val fakeLoginRepo = FakeLoginRepo(fakeLoginDataSource)
        val fakeLoginUseCases = LoginUseCases(fakeLoginRepo)
        loginViewModel = LoginViewModel(fakeLoginUseCases)

        val expectedUIState = LoginUiState.Success(TestDataUtil.getLoginResponseModel())
        loginViewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()
        val actualUiState = loginViewModel.uiState.value
        assert(expectedUIState == actualUiState)
    }

}