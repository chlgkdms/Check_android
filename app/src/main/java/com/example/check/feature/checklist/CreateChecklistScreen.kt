package com.example.check.feature.checklist

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.designsystem.textfield.CheckTextField
import com.example.check.feature.home.HomeSideEffect
import com.example.check.feature.home.HomeViewModel
import com.example.check.feature.onboarding.CheckButton

@Composable
internal fun CreateChecklistScreen(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    val checklistItems = remember { mutableStateListOf<String>() }
    val newItemText = remember { mutableStateOf("") }
    val context = LocalContext.current

    val (title, onTitleChange) = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when(it) {
                HomeSideEffect.Success -> {
                    navController.popBackStack()
                    Toast.makeText(context, "작성을 성공했습니다", Toast.LENGTH_SHORT).show()
                }
                HomeSideEffect.Failure -> Toast.makeText(context, "작성을 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*viewModel.sideEffect.collect {

        *//*when (it) {
            it.Failure -> Toast.makeText(
                context,
                "작성을 실패했습니다.",
                Toast.LENGTH_SHORT
            ).show()

            CreateSubjectSideEffect.Success -> {
                navController.popBackStack()
                Toast.makeText(context, "작성되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }*//*
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CheckTextField(
            value = title,
            onValueChange = onTitleChange,
            hint = "제목을 입력하세요",
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
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
                }, modifier = Modifier.size(48.dp) // IconButton의 크기 설정
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
                    hint = "항목 ${index + 1}",
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .width(330.dp),
                    singleLine = true,
                )
                IconButton(
                    onClick = { checklistItems.removeAt(index) },
                    modifier = Modifier.size(48.dp) // IconButton의 크기 설정
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete item")
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        CheckButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.createChecklist(
                    title = title,
                    content = checklistItems,
                )
            },
            text = "작성 완료",
        )
        Spacer(modifier = Modifier.width(40.dp))
    }
}