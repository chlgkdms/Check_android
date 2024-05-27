package com.example.check.designsystem.theme

import Body
import Body2
import BodyLarge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.check.R
import com.example.check.navigation.NavigationRoute

@Composable
internal fun CheckBottomAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    BottomAppBar(
        containerColor = Color.White,
        modifier = modifier.graphicsLayer {
            clip = true
            shape = RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
            )
            shadowElevation = 20f
        },
        contentPadding = PaddingValues(
            top = 4.dp,
            start = 8.dp,
            end = 8.dp,
        ),
    ) {
        MainSections.entries.forEach { section ->
            val selected = currentRoute == section.route
            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(section.route) },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = section.iconRes),
                        contentDescription = section.label,
                    )
                },
                label = {
                    Body2(
                        text = section.label,
                        color = if (selected) Color(0xFF67BAF5) else Color.Black,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF67BAF5),
                    selectedTextColor = Color(0xFF67BAF5),
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black,
                ),
            )
        }
    }
}

@Immutable
private enum class MainSections(
    val route: String,
    @DrawableRes val iconRes: Int,
    val label: String,
) {
    HOME(
        route = NavigationRoute.Root.HOME,
        iconRes = R.drawable.ic_home,
        label = "홈",
    ),
    QUESTION(
        route = NavigationRoute.Root.ROUTINE,
        iconRes = R.drawable.ic_routine,
        label = "루틴",
    ),
    PROFILE(
        route = NavigationRoute.Root.PROFILE,
        iconRes = R.drawable.ic_profile,
        label = "프로필",
    ),
}
