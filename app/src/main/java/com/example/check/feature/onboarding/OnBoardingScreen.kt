package com.example.check.feature.onboarding

import Body
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.R

@Composable
internal fun OnBoardingScreen(
    navController: NavController,
    navigateToSignUp: () -> Unit,
    navigateToSignIn: () -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Image(
            modifier = Modifier.size(178.dp),
            painter = painterResource(id = R.drawable.ic_on_boarding),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.img_on_boarding),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.weight(1f))
        CheckButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navigateToSignUp() },
            text = "회원가입",
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Body(text = "이미 체크에 가입했다면,")
            Spacer(modifier = Modifier.width(6.dp))
            Body(
                modifier = Modifier.clickable { navigateToSignIn() },
                text = "로그인",
                color = Color(0xFF67BAF5),
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
internal fun CheckButton(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF67BAF5),
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Text(text = text)
    }
}