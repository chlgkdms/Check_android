package com.example.check.feature.profile

import BodyLarge
import Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.check.R
import com.example.check.designsystem.theme.CheckColor

@Composable
internal fun ProfileScreen(
    navController: NavController,
) {
    val nickname = "하은"

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
                .padding(
                    vertical = 18.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile_image),
                contentDescription = "icon profile image",
            )
            Spacer(modifier = Modifier.height(22.dp))
            Row {
                BodyLarge(
                    text = nickname,
                    color = Color(0xFF67BAF5),
                )
                BodyLarge(
                    text = "님 안녕하세요!",
                    color = Color.Black,
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))

    }
}