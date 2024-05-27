package com.example.check.navigation

sealed class NavigationRoute(val route: String) {

    data object Root : NavigationRoute(route = "/root") {
        val HOME = "$route/home"
        val ROUTINE = "$route/routine"
        val PROFILE = "$route/profile"
    }

    data object Main : NavigationRoute(route = "/main") {
        val MAIN = "$route/root"
    }

    data object Auth : NavigationRoute(route = "/auth") {
        val ON_BOARDING = "$route/onBoarding"
        val SPLASH = "$route/splash"
        val SIGN_IN = "$route/signIn"
        val SIGN_UP = "$route/signUp"
    }
}