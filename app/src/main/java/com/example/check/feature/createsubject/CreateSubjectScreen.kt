package com.example.check.feature.createsubject

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.check.designsystem.textfield.CheckTextField
import com.example.check.designsystem.theme.CheckColor
import com.example.check.feature.onboarding.CheckButton
import com.example.check.feature.subjectdetail.Header

@Composable
internal fun CreateSubjectScreen(
    navController: NavController,
    createSubjectViewModel: CreateSubjectViewModel = hiltViewModel(),
) {
    val state by createSubjectViewModel.state.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        createSubjectViewModel.sideEffect.collect {
            when (it) {
                CreateSubjectSideEffect.Failure -> Toast.makeText(
                    context,
                    "작성을 실패했습니다.",
                    Toast.LENGTH_SHORT
                ).show()

                CreateSubjectSideEffect.Success -> {
                    navController.popBackStack()
                    Toast.makeText(context, "작성되었습니다.", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    Column(
        modifier = Modifier
            .background(color = CheckColor.White)
            .padding(horizontal = 16.dp),
    ) {
        Header(
            onClick = navController::popBackStack,
        )
        CheckTextField(
            value = state.subjectName,
            onValueChange = createSubjectViewModel::setSubjectName,
            hint = "주제를 입력하세요",
            title = "주제",
        )
        Spacer(modifier = Modifier.weight(1f))
        CheckButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                createSubjectViewModel.createSubject()
            },
            text = "작성 완료",
        )
        Spacer(modifier = Modifier.height(40.dp))
    }

}