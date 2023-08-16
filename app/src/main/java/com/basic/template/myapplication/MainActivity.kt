package com.basic.template.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basic.template.myapplication.login.ui.LoginScreen
import com.basic.template.myapplication.login.ui.LoginViewModel
import com.basic.template.myapplication.screen.HomeScreen
import com.basic.template.myapplication.screen.LoginScreen
import com.basic.template.myapplication.screen.RegisterScreen
import com.basic.template.myapplication.screen.SampleScreen
import com.basic.template.myapplication.screen.SplashScreen
import com.basic.template.myapplication.ui.theme.MyApplicationTheme
import com.basic.template.myapplication.userlist.ui.UserListScreen
import com.basic.template.myapplication.userlist.ui.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = LoginScreen.route
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ) {
//        composable(SplashScreen.route){
//            SplashScreen(navController)
//        }
        composable(LoginScreen.route) {
            val userName = remember {
                mutableStateOf(TextFieldValue("eve.holt@reqres.in"))
            }
            val password = remember {
                mutableStateOf(TextFieldValue("cityslicka"))
            }
            val loginViewModelObj: LoginViewModel = hiltViewModel()

            LoginScreen(
                navController,
                onNavigateToRegister = { navController.navigate(RegisterScreen.route) },
                userName = userName, password = password, loginViewModel = loginViewModelObj
            )

        }

        composable(HomeScreen.route) {
            val userListViewModel: UserListViewModel = hiltViewModel()
            UserListScreen(navController, userListViewModel)
        }

        composable(SampleScreen.route) {
            SampleScreen()
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        LoginScreen()
    }
}