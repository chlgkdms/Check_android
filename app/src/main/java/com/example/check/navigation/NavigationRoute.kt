package com.example.check.navigation

sealed class NavigationRoute(val route: String) {

    data object Root : NavigationRoute(route = "/root") {
        val HOME = "$route/home"
        val ROUTINE = "$route/routine"
        val PROFILE = "$route/profile"
    }

    data object Main : NavigationRoute(route = "/main") {
        val MAIN = "$route/root"
        val SUBJECT_DETAIL = "$route/subject/details"
        val CREATE_ROUTINE = "$route/routine/create"
        val CREATE_SUBJECT = "$route/subject/create"
        val CREATE_CHECKLIST = "$route/checklist/create"
    }

    data object Auth : NavigationRoute(route = "/auth") {
        val ON_BOARDING = "$route/onBoarding"
        val SPLASH = "$route/splash"
        val SIGN_IN = "$route/signIn"
        val SIGN_UP = "$route/signUp"
    }

    data object Arguments {
        val SUBJECT_ID = "{subject-id}"
    }
}