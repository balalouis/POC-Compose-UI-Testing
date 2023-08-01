package com.basic.template.myapplication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.basic.template.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LaunchedEffect(Unit) {
            delay(4000)
            navController.navigate(LoginScreen.route) {
                popUpTo(SplashScreen.route) {
                    inclusive = true
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null
        )
    }
}