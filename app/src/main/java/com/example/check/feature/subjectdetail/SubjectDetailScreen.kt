package com.example.check.feature.subjectdetail

import Body2
import BodyLarge2
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavController
import com.example.check.R
import com.example.check.data.remote.model.subject.response.SubjectDetailResponse
import com.example.check.designsystem.theme.CheckColor
import com.example.check.feature.home.HomeViewModel
import com.example.check.navigation.NavigationRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun SubjectDetailScreen(
    navController: NavController,
    subjectId: Long,
    viewModel: HomeViewModel,
) {
    val state by viewModel.state.collectAsState()
    val details = state.details
    val checklists = details.checklist

    LaunchedEffect(Unit) {
        viewModel.setId(subjectId)
        viewModel.fetchSubjectDetail()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Color(0xFFB9E6FF),
                contentColor = Color.White,
                onClick = { navController.navigate(NavigationRoute.Main.CREATE_CHECKLIST) },
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
                .background(color = CheckColor.White)
                .fillMaxSize()
        ) {
            Header(
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = navController::popBackStack,
                title = details.subjectName,
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(checklists.orEmpty()) {
                    CheckListItem(
                        checklistId = it.checkListId,
                        checklist = it.checklistContentList,
                        title = it.title,
                        writer = it.nickname,
                        date = it.date,
                        isSaved = it.isSaved,
                        onClick = { checklistId ->
                            viewModel.updateSaved()
                            viewModel.checklistId(checklistId)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun CheckListItem(
    checklistId: Long,
    checklist: List<SubjectDetailResponse.CheckList.ChecklistContentList>,
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
                horizontal = 16.dp, vertical = 4.dp
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
                .padding(
                    horizontal = 16.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Body2(text = writer)
            Body2(text = date.split('T')[0])
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            checklist.forEach { checklistContentItem ->
                ChecklistContentItem(
                    content = checklistContentItem.content,
                    isCleared = checklistContentItem.isCleared
                )
            }
        }
    }
}

@Composable
fun ChecklistContentItem(
    content: String, isCleared: Boolean
) {
    var check by remember { mutableStateOf(isCleared) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    check = !check
                },
            painter = painterResource(
                id = if (check) R.drawable.ic_checkbox_filled
                else R.drawable.ic_checkbox_outlined
            ),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Body2(text = content)
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
            .background(CheckColor.White)
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