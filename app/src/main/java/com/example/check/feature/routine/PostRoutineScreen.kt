package com.example.check.feature.routine

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.designsystem.textfield.CheckTextField
import com.example.check.feature.subjectdetail.Header

@Composable
internal fun PostRoutineScreen(
    navController: NavController,
) {
    val checklistItems = remember { mutableStateListOf<String>() }
    val newItemText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Header(
            onClick = navController::popBackStack,
            title = "루틴 추가하기",
        )
        // 체크리스트 항목 추가 버튼
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // CheckTextField로 변경
            CheckTextField(
                value = newItemText.value,
                onValueChange = { newItemText.value = it },
                hint = "추가할 항목",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .width(330.dp),
                singleLine = true,
            )
            IconButton(
                onClick = {
                    if (newItemText.value.isNotBlank()) {
                        checklistItems.add(newItemText.value)
                        newItemText.value = ""
                    }
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add item")
            }
        }

        // 동적으로 생성된 체크리스트 항목들
        checklistItems.forEachIndexed { index, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                // CheckTextField로 변경
                CheckTextField(
                    value = item,
                    onValueChange = { checklistItems[index] = it },
                    hint = "항목 $index",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(330.dp),
                    singleLine = true,
                )
                IconButton(
                    onClick = { checklistItems.removeAt(index) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete item")
                }
            }
        }
    }
}
