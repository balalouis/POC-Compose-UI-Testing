package com.basic.template.myapplication.screen

interface ScreenDestination {
    val route: String
}

object SplashScreen : ScreenDestination {
    override val route: String
        get() = "Splash"
}

object LoginScreen : ScreenDestination {
    override val route: String
        get() = "Login"
}

object RegisterScreen : ScreenDestination {
    override val route: String
        get() = "Register"
}

object HomeScreen : ScreenDestination {
    override val route: String
        get() = "Home"
}

object DetailScreen : ScreenDestination {
    override val route: String
        get() = "Detail"
}

object SampleScreen : ScreenDestination{
    override val route: String
        get() = "Sample"

}