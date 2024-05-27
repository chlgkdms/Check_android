package com.example.check.feature.profile

import Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
internal fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Title(
            modifier = Modifier.padding(top = 30.dp),
            text = "프로필",
            color = Color.Black,
        )
        
    }
}