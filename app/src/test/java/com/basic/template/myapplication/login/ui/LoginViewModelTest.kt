package com.basic.template.myapplication.login.ui

import com.basic.template.myapplication.CoroutineTestRule
import com.basic.template.myapplication.TestDataUtil
import com.basic.template.myapplication.fake.FakeLoginDataSource
import com.basic.template.myapplication.fake.FakeLoginRepo
import com.basic.template.myapplication.login.domain.usecases.LoginUseCases
import com.basic.template.myapplication.model.LoginResponseModel
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var loginViewModel: LoginViewModel

    @Test
    fun testLoginSuccess() {
        // Arrange
        val fakeLoginDataSource = FakeLoginDataSource(isApiSuccess = true)
        val fakeLoginRepo = FakeLoginRepo(fakeLoginDataSource)
        val fakeLoginUseCases = LoginUseCases(fakeLoginRepo)
        loginViewModel = LoginViewModel(fakeLoginUseCases)

        // Act
        val expectedData = TestDataUtil.getLoginSuccessResponse()
        loginViewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        // Assert
        val actualUiData = loginViewModel.uiState.value.data as LoginResponseModel
        assert(expectedData == actualUiData)
    }

    @Test
    fun testLoginFailure() {
        // Arrange
        val fakeLoginDataSource = FakeLoginDataSource(isApiSuccess = false)
        val fakeLoginRepo = FakeLoginRepo(fakeLoginDataSource)
        val fakeLoginUseCases = LoginUseCases(fakeLoginRepo)
        loginViewModel = LoginViewModel(fakeLoginUseCases)

        // Act
        val expectedData = TestDataUtil.getFailureResponse()
        loginViewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        // Assert
        val actualUiData = loginViewModel.uiState.value.message
        assert(expectedData == actualUiData)
    }
}