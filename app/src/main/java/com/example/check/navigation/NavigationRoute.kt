package com.example.check.navigation

sealed class NavigationRoute(val route: String) {
    data object Auth : NavigationRoute(route = "/auth") {
        val ON_BOARDING = "$route/onBoarding"
        val SPLASH = "$route/splash"
        val SIGN_IN = "$route/signIn"
        val SIGN_UP = "$route/signUp"
    }
}