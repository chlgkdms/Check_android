package com.example.check.feature.home

import Body2
import BodyLarge2
import SubTitle
import Title
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.check.designsystem.theme.CheckColor
import com.example.check.navigation.NavigationRoute


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFFB9E6FF),
                contentColor = Color.White,
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = "floating button icon",
                )
            }
        },
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CheckColor.White)
                .padding(horizontal = 16.dp),
        ) {
            Title(
                modifier = Modifier.padding(top = 30.dp),
                text = "홈",
                color = Color.Black,
            )
            SubTitle(
                modifier = Modifier.padding(
                    top = 60.dp,
                    bottom = 14.dp,
                ),
                text = "주제",
                color = Color(0xFF7F7F7F),
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(homeViewModel.subjects) {
                    SubjectItem(
                        subjectTitle = it.subjectName,
                        writer = it.writer,
                        onClick = {
                            navController.navigate("${NavigationRoute.Main.SUBJECT_DETAIL}/${it}")
                        },
                    )
                }
            }
        }
    }


}

@Composable
private fun SubjectItem(
    subjectTitle: String,
    writer: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
            )
            .padding(horizontal = 20.dp, vertical = 15.dp),
    ) {
        BodyLarge2(
            text = subjectTitle,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Body2(
            text = writer,
        )
    }
}