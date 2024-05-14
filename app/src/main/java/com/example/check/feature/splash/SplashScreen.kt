package com.example.check.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.navigation.NavigationRoute
import kotlinx.coroutines.delay
import com.example.check.R

@Composable
internal fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate(NavigationRoute.Auth.ON_BOARDING)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = R.drawable.ic_check_logo),
            contentDescription = null,
        )
    }
}