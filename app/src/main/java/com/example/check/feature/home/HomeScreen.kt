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
import androidx.navigation.NavController
import com.example.check.navigation.NavigationRoute


data class Subject(
    val subjectId: Long,
    val title: String,
    val writer: String,
)

val subjects = mutableListOf(
    Subject(
        subjectId = 1,
        title = "기숙사",
        writer = "김주원",
    ),
    Subject(
        subjectId = 2,
        title = "학교",
        writer = "최하은",
    ),
    Subject(
        subjectId = 3,
        title = "여행",
        writer = "홍길동",
    ),
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun HomeScreen(navController: NavController) {
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
            modifier = Modifier.padding(horizontal = 16.dp),
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
                items(subjects) {
                    SubjectItem(
                        subjectTitle = it.title,
                        writer = it.writer,
                        onClick = {
                            navController.navigate("${NavigationRoute.Main.SUBJECT_DETAIL}/${it}")
                        }
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