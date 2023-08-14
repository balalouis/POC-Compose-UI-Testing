package com.basic.template.myapplication

import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basic.template.myapplication.api.ApiWebService
import com.basic.template.myapplication.login.data.datasource.LoginDataSource
import com.basic.template.myapplication.login.domain.repo.LoginRepo
import com.basic.template.myapplication.login.domain.usecases.LoginUseCases
import com.basic.template.myapplication.login.ui.LoginScreen
import com.basic.template.myapplication.login.ui.LoginViewModel
import com.basic.template.myapplication.screen.LoginScreen
import com.basic.template.myapplication.screen.RegisterScreen
import com.basic.template.myapplication.screen.SampleScreen
import com.basic.template.myapplication.ui.theme.MyApplicationTheme
import com.basic.template.myapplication.util.TestUITag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavController

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var loginUseCases: LoginUseCases

    private lateinit var loginRepo: LoginRepo

    private lateinit var loginDataSource: LoginDataSource

    private lateinit var apiWebService: ApiWebService

    @Before
    fun init() {
        hiltTestRule.inject()
    }

    @Test
    fun testSampleUiViews() {
        launchLoginScreenNavGraph()
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
    }

    @Test
    fun testLoginUI() {
        launchLoginScreenNavGraph()
        // UI is visible
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.password))
        composeTestRule
            .onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.login))

        // Typing the email and password
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_FIELD_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_FILED_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_password))

        // Validating the UI after entering the text
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_FIELD_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.test_user_email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_FILED_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.test_user_password))
    }

    private fun launchLoginScreenNavGraph() {
        composeTestRule.activity.setContent {
            MyApplicationTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController as NavHostController,
                    startDestination = LoginScreen.route
                ) {
                    composable(SampleScreen.route) {
                        SampleScreen()
                    }

                    composable(LoginScreen.route) {
                        val userName = remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        val password = remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        val loginViewModelObj: LoginViewModel = hiltViewModel()

                        LoginScreen(
                            navController,
                            onNavigateToRegister = { navController.navigate(RegisterScreen.route) },
                            userName = userName,
                            password = password,
                            loginViewModel = loginViewModelObj
                        )

                    }
                }
            }
        }
    }
}