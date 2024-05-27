package com.example.check.navigation

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.check.designsystem.theme.CheckBottomAppBar
import com.example.check.feature.home.HomeScreen
import com.example.check.feature.profile.ProfileScreen
import com.example.check.feature.routine.RoutineScreen

@Composable
internal fun RootScreen(
    navController: NavController,
) {
    val navHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            CheckBottomAppBar(navController = navHostController)
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navHostController,
            startDestination = NavigationRoute.Root.HOME,
        ) {
            composable(route = NavigationRoute.Root.HOME) {
                HomeScreen(navController = navController)
            }

            composable(route = NavigationRoute.Root.ROUTINE) {
                RoutineScreen(navController = navHostController)
            }

            composable(route = NavigationRoute.Root.PROFILE) {
                ProfileScreen(navController = navHostController)
            }
        }
    }
}