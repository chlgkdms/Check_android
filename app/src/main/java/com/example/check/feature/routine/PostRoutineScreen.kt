package com.example.check.feature.routine

import Title
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
internal fun PostRoutineScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
    ) {
        Title(
            modifier = Modifier.padding(top = 30.dp),
            text = "루틴 작성",
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(30.dp))



    }
}