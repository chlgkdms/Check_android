package com.example.check.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.check.feature.onboarding.OnBoardingScreen
import com.example.check.feature.signin.SignInScreen
import com.example.check.feature.signup.SignUpScreen
import com.example.check.feature.splash.SplashScreen
import com.example.check.feature.subjectdetail.SubjectDetail

@Composable
internal fun CheckApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = NavigationRoute.Auth.route
    ) {
        auth(navController = navController)
        main(navController = navController)
    }
}

private fun NavGraphBuilder.auth(navController: NavController) {
    navigation(
        route = NavigationRoute.Auth.route,
        startDestination = NavigationRoute.Auth.SPLASH,
    ) {
        composable(route = NavigationRoute.Auth.SPLASH) {
            SplashScreen(navController = navController)
        }

        composable(route = NavigationRoute.Auth.ON_BOARDING) {
            OnBoardingScreen(
                navController = navController,
                navigateToSignUp = { navController.navigate(NavigationRoute.Auth.SIGN_UP) },
                navigateToSignIn = { navController.navigate(NavigationRoute.Auth.SIGN_IN) },
            )
        }

        composable(route = NavigationRoute.Auth.SIGN_IN) {
            SignInScreen(
                navController = navController,
                navigateToSignUp = {},
                navigateToMain = {
                    navController.navigate(NavigationRoute.Main.MAIN) {
                        popUpTo(0)
                    }
                },
            )
        }

        composable(route = NavigationRoute.Auth.SIGN_UP) {
            SignUpScreen(navController = navController,
                navigateToSignIn = { navController.navigate(NavigationRoute.Auth.SIGN_IN) })
        }
    }
}

private fun NavGraphBuilder.main(navController: NavController) {
    navigation(
        route = NavigationRoute.Main.route, startDestination = NavigationRoute.Main.MAIN
    ) {
        composable(route = NavigationRoute.Main.MAIN) {
            RootScreen(navController = navController)
        }

        composable(
            route = "${NavigationRoute.Main.SUBJECT_DETAIL}/${NavigationRoute.Arguments.SUBJECT_ID}",
            arguments = listOf(
                navArgument(
                    name = "subject-id",
                    builder = { type = NavType.LongType },
                )
            )
        ) {
            val subjectId = it.arguments?.getLong("subject-id") ?: 0L
            SubjectDetail(
                navController = navController,
            )
        }
    }
}