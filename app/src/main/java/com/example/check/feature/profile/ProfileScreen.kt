package com.example.check.feature.profile

import Body2
import BodyLarge
import BodyLarge2
import Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.check.R
import com.example.check.designsystem.theme.CheckColor
import com.example.check.feature.home.HomeViewModel

@Composable
internal fun ProfileScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val profileChecklist = state.profileChecklist

    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CheckColor.White)
            .padding(horizontal = 16.dp),
    ) {
        Title(
            modifier = Modifier.padding(top = 30.dp),
            text = "프로필",
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color(0xFF67BAF5),
                    shape = RoundedCornerShape(30.dp),
                )
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_image),
                contentDescription = "icon profile image",
            )
            Spacer(modifier = Modifier.height(22.dp))
            Row {
                BodyLarge(
                    text = state.name,
                    color = Color(0xFF67BAF5),
                )
                BodyLarge(
                    text = "님 안녕하세요!",
                    color = Color.Black,
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn {
            items(profileChecklist.orEmpty()) { checklistItem ->
                CheckListItem(
                    checklistId = checklistItem.id,
                    checklist = checklistItem.content,
                    title = checklistItem.title,
                    writer = checklistItem.writer,
                    date = checklistItem.date,
                    isSaved = checklistItem.isSaved,
                    onClick = { checklistId ->
                        viewModel.checklistId(checklistId)
                        viewModel.updateSaved()
                    },
                )
                Spacer(modifier = Modifier.height(8.dp)) // 각 항목 사이 간격 추가
            }
        }
    }
}

@Composable
private fun CheckListItem(
    checklistId: Long,
    checklist: List<String>,
    title: String,
    writer: String,
    date: String,
    isSaved: Boolean,
    onClick: (Long) -> Unit,
) {
    var save by remember { mutableStateOf(isSaved) }

    Column(
        modifier = Modifier
            .padding(
                vertical = 4.dp,
            )
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .background(
                color = CheckColor.White,
                shape = RoundedCornerShape(10.dp),
            )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BodyLarge2(text = title)
            Icon(
                modifier = Modifier.clickable {
                    save = !save
                    onClick(checklistId)
                },
                imageVector = if (save) Icons.Outlined.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Body2(text = writer)
            Body2(text = date.split('T')[0]) // ISO 날짜 형식에서 날짜 부분만 가져옴
        }
        Spacer(modifier = Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            checklist.forEach { content ->
                Body2(text = content)
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}