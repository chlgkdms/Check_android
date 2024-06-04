package com.example.check.feature.profile

import BodyLarge
import Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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

@Composable
internal fun ProfileScreen(navController: NavController) {
    val nickname = "하은"

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
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
        ProfileCard(
            icon = painterResource(id = R.drawable.ic_check_list),
            text = "내가 작성한 체크리스트",
            description = "icon check list",
        )
        Spacer(modifier = Modifier.height(10.dp))
        ProfileCard(
            icon = painterResource(id = R.drawable.ic_saved_checklist),
            text = "내가 저장한 체크리스트",
            description = "icon saved checklist",
        )
    }
}

@Composable
fun ProfileCard(
    icon: Painter,
    text: String,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 12.dp,
                )
                .size(26.dp),
            painter = icon,
            contentDescription = description,
        )
        BodyLarge(
            modifier = Modifier.padding(end = 130.dp),
            text = text,
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = "icon arrow right",
        )
    }
}