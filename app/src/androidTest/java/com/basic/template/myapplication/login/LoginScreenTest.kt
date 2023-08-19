package com.basic.template.myapplication.login

import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basic.template.myapplication.MainActivity
import com.basic.template.myapplication.R
import com.basic.template.myapplication.hilt.AppModule
import com.basic.template.myapplication.hilt.NetworkModule
import com.basic.template.myapplication.login.ui.LoginScreen
import com.basic.template.myapplication.login.ui.LoginViewModel
import com.basic.template.myapplication.screen.HomeScreen
import com.basic.template.myapplication.screen.LoginScreen
import com.basic.template.myapplication.screen.RegisterScreen
import com.basic.template.myapplication.screen.SampleScreen
import com.basic.template.myapplication.ui.theme.MyApplicationTheme
import com.basic.template.myapplication.userlist.ui.UserListScreen
import com.basic.template.myapplication.userlist.ui.UserListViewModel
import com.basic.template.myapplication.util.TestUITag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltTestRule.inject()
        launchLoginScreenNavGraph()
    }

    @Test
    fun testSampleUiViews() {
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
    }

    @Test
    fun testLoginUI() {
        loginUIValidation()
    }

    @Test
    fun testLoginFlowViaUntil() {
        typeUserInput()
        composeTestRule.onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG).performClick()
        composeTestRule.waitUntil(timeoutMillis = 6000) {
            composeTestRule
                .onAllNodesWithTag(TestUITag.PROGRESS_BAR)
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.waitUntil(timeoutMillis = 4000) {
            composeTestRule.onAllNodesWithTag(TestUITag.USER_LIST_TITLE)
                .fetchSemanticsNodes().size == 1
        }

    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLoginFlowViaUntilAdditional() {
        typeUserInput()
        composeTestRule.onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG).performClick()
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestUITag.PROGRESS_BAR),
            timeoutMillis = 6000
        )
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(TestUITag.USER_LIST_TITLE),
            timeoutMillis = 6000
        )
    }

    private fun typeUserInput() {
        // Typing the email and password
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_FIELD_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_FILED_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_password))
    }

    private fun loginUIValidation() {
        // UI is visible
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.password))
        composeTestRule
            .onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.login))

        typeUserInput()

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

                    composable(HomeScreen.route) {
                        val userListViewModel: UserListViewModel = hiltViewModel()
                        UserListScreen(navController, userListViewModel)
                    }
                }
            }
        }
    }
}