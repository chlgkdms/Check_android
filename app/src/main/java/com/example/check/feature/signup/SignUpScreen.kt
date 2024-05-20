package com.example.check.feature.signup

import Body
import SubTitle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.R
import com.example.check.designsystem.textfield.CheckTextField
import com.example.check.feature.onboarding.CheckButton

@Composable
internal fun SignUpScreen(navController: NavController) {

    val (nickname, onNicknameChange) = remember {
        mutableStateOf("")
    }
    val (accountId, onAccountIdChange) = remember {
        mutableStateOf("")
    }
    val (password, onPasswordChange) = remember {
        mutableStateOf("")
    }
    val (passwordCheck, onPasswordCheckChange) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_check_logo2),
            contentDescription = "ic check logo2",
        )
        Spacer(modifier = Modifier.height(35.dp))
        SubTitle(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "회원가입",
            color = Color(0xFF67BAF5),
        )
        Spacer(modifier = Modifier.height(35.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            CheckTextField(
                value = nickname,
                onValueChange = onNicknameChange,
                hint = "닉네임을 입력하세요.",
                title = "닉네임",
            )
            Spacer(modifier = Modifier.height(10.dp))
            CheckTextField(
                value = accountId,
                onValueChange = onAccountIdChange,
                hint = "아이디를 입력하세요.",
                title = "아이디",
            )
            Spacer(modifier = Modifier.height(10.dp))
            CheckTextField(
                value = password,
                onValueChange = onPasswordChange,
                hint = "비밀번호를 입력하세요.",
                title = "비밀번호",
                isPassword = true,
            )
            Spacer(modifier = Modifier.height(10.dp))
            CheckTextField(
                value = passwordCheck,
                onValueChange = onPasswordCheckChange,
                hint = "비밀번호를 다시 입력하세요.",
                title = "비밀번호",
                isPassword = true,
            )

            Spacer(modifier = Modifier.weight(1f))
            CheckButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /**/ },
                text = "로그인",
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Body(text = "가입되어 있지 않다면,")
                Spacer(modifier = Modifier.width(6.dp))
                Body(
                    modifier = Modifier.clickable { /**/ },
                    text = "회원가입",
                    color = Color(0xFF67BAF5),
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }

}