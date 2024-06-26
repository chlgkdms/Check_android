package com.example.check.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.check.feature.checklist.CreateChecklistScreen
import com.example.check.feature.createsubject.CreateSubjectScreen
import com.example.check.feature.home.HomeViewModel
import com.example.check.feature.onboarding.OnBoardingScreen
import com.example.check.feature.routine.PostRoutineScreen
import com.example.check.feature.signin.SignInScreen
import com.example.check.feature.signup.SignUpScreen
import com.example.check.feature.splash.SplashScreen
import com.example.check.feature.subjectdetail.SubjectDetailScreen

@Composable
internal fun CheckApp() {
    val navController = rememberNavController()
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Auth.route,
    ) {
        auth(navController = navController)
        main(
            navController = navController,
            homeViewModel = homeViewModel,
        )
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

private fun NavGraphBuilder.main(
    navController: NavController,
    homeViewModel: HomeViewModel,
) {
    navigation(
        route = NavigationRoute.Main.route,
        startDestination = NavigationRoute.Main.MAIN,
    ) {
        composable(route = NavigationRoute.Main.MAIN) {
            RootScreen(
                navController = navController,
                viewModel = homeViewModel,
            )
        }

        composable(
            route = "${NavigationRoute.Main.SUBJECT_DETAIL}/${NavigationRoute.Arguments.SUBJECT_ID}",
            arguments = listOf(
                navArgument(
                    name = "subject-id",
                    builder = { type = NavType.LongType },
                )
            ),
        ) {
            val subjectId = it.arguments?.getLong("subject-id") ?: 0L
            SubjectDetailScreen(
                navController = navController,
                subjectId = subjectId,
                viewModel = homeViewModel,
            )
        }

        composable(route = NavigationRoute.Main.CREATE_ROUTINE) {
            PostRoutineScreen(navController)
        }

        composable(route = NavigationRoute.Main.CREATE_SUBJECT) {
            CreateSubjectScreen(navController)
        }

        composable(route = NavigationRoute.Main.CREATE_CHECKLIST) {
            CreateChecklistScreen(
                navController,
                viewModel = homeViewModel,
            )
        }
    }
}