package com.basic.template.myapplication.login.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.basic.template.myapplication.R
import com.basic.template.myapplication.model.LoginRequestModel
import com.basic.template.myapplication.model.LoginUiState
import com.basic.template.myapplication.screen.HomeScreen
import com.basic.template.myapplication.screen.LoginScreen
import com.basic.template.myapplication.util.TestUITag
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavController,
    onNavigateToRegister: (Int) -> Unit,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    loginViewModel: LoginViewModel
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        LoginText()
        LoginTextFields(userName, password)
        LoginButton(
            navController,
            loginViewModel,
            userName = userName,
            password = password
        )
        SignUp(onNavigateToRegister)
    }
}

@Composable
fun LoginText() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.login), modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            ), fontSize = 24.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun LoginTextFields(
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = userName.value,
            onValueChange = { userName.value = it },
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    modifier = Modifier.testTag(TestUITag.EMAIL_TAG)
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
                .testTag(TestUITag.EMAIL_FIELD_TAG),
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    modifier = Modifier.testTag(TestUITag.PASSWORD_TAG)
                )
            },
            singleLine = true,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.dp_8))
                .fillMaxWidth()
                .testTag(TestUITag.PASSWORD_FILED_TAG),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
        )
    }
}

@Composable
fun LoginButton(
    navController: NavController,
    loginViewModel: LoginViewModel,
    userName: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    val loginRequestModel =
        LoginRequestModel(email = userName.value.text, password = password.value.text)
    val scope = rememberCoroutineScope()
    val uiState by loginViewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    if (uiState is LoginUiState.Success) {
        val token = (uiState as LoginUiState.Success).loginResponseModel?.token
        Log.d("=====> ", "" + token)
        if (token?.isNotEmpty() == true) {
            LaunchedEffect(Unit) {
                navController.navigate(HomeScreen.route) {
                    popUpTo(LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
            LaunchedEffect(Unit) {
                snackBarHostState.showSnackbar(
                    "Hello world",
                    "Dummy",
                    duration = SnackbarDuration.Long
                )
            }

        }
    } else if (uiState is LoginUiState.Error) {
        Log.d("-----> ", "Error: ")
    } else if (uiState is LoginUiState.Loading) {
        ProgressBar()
    }
    Button(
        onClick = {
            scope.launch {
                loginViewModel.loginApiViewModel(loginRequestModel)
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(
                    id = R.dimen.dp_8
                ), end = dimensionResource(id = R.dimen.dp_8)
            )
            .testTag(TestUITag.LOGIN_BUTTON_TAG)
    ) {
        Text(text = stringResource(id = R.string.login))
    }
}


@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestUITag.PROGRESS_BAR),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun SignUp(onClickToRegister: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.do_not_have_an_account)),
            onClick = onClickToRegister,
            modifier = Modifier.padding(
                all = dimensionResource(
                    id = R.dimen.dp_8
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
//    LoginScreen()
}