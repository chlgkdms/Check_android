package com.example.check.feature.subjectdetail

import Body2
import BodyLarge2
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.check.designsystem.theme.CheckColor
import com.example.check.feature.home.HomeViewModel

data class SubjectDetail(
    val title: String,
    val writer: String,
    val date: String,
)

val subjectDetails = listOf(
    SubjectDetail(
        title = "기숙사",
        writer = "하은",
        date = "2024-10-25",
    ),
    SubjectDetail(
        title = "등산",
        writer = "민채",
        date = "2024-10-25",
    ),
    SubjectDetail(
        title = "여행",
        writer = "길동",
        date = "2024-10-25",
    ),
)
@Composable
internal fun SubjectDetailScreen(
    navController: NavController,
    subjectId: Long,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(color = CheckColor.White)
            .padding(horizontal = 16.dp),
    ) {
        Header(
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = navController::popBackStack,
        )
        /*LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(subjectDetails) {
                CheckListItem(
                    title =,
                    writer =,
                    date =,
                )
            }
        }*/
    }


}

@Composable
fun CheckListItem(
    title: String,
    writer: String,
    date: String,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BodyLarge2(text = title)
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "",
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Body2(text = writer)
            Body2(text = date.split('T')[0])
        }
    }
}

@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9F9))
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Body2(text = title ?: "")
    }
}