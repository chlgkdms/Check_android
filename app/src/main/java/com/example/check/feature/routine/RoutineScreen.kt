package com.example.check.feature.routine

import Body2
import BodyLarge2
import Title
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.check.R
import com.example.check.designsystem.theme.CheckColor
import com.example.check.navigation.NavigationRoute
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun RoutineScreen(
    navHostController: NavHostController,
    navController: NavController,
) {
    val coroutineScope = rememberCoroutineScope()

    var isInitialized by remember { mutableStateOf(false) }

    var currentReward by remember { mutableStateOf(0) }
    val rank = getRank(currentReward)


    val animateProgress by animateFloatAsState(
        targetValue = if (!isInitialized) 0f
        else if ((currentReward.toFloat() - rank.minReward) / (rank.maxReward - rank.minReward) > 1f) 1f
        else 1f,
        //else 1f,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing,
        ),
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CheckColor.White)
            .padding(horizontal = 16.dp),
    ) {
        Title(
            modifier = Modifier.padding(top = 30.dp),
            text = "루틴",
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(54.dp))
        RoutineProgress(
            routine = 10,
            coroutineScope = coroutineScope,
            isInitialize = isInitialized,
            animateProgress = animateProgress,
        )
        Spacer(modifier = Modifier.height(15.dp))
        BodyLarge2(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "루틴 달성률 : 50%",
            color = CheckColor.Primary100,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(NavigationRoute.Main.CREATE_ROUTINE)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "routine add icon",
            )
            Spacer(modifier = Modifier.width(5.dp))
            Body2(text = "루틴 추가")
        }
    }
}

@Composable
private fun RoutineProgress(
    routine: Int,
    coroutineScope: CoroutineScope,
    isInitialize: Boolean,
    animateProgress: Float,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(162.dp)
                .clip(CircleShape)
                .background(CheckColor.Gray300),
            contentAlignment = Alignment.Center,
        ) {
            BodyLarge2(
                text = routine.toString(),
                color = Color.White,
            )
        }
        CircularProgressIndicator(
            modifier = Modifier
                .size(202.dp)
                .clip(CircleShape),
            progress = 1f,
            color = CheckColor.Gray300,
            strokeWidth = 8.dp,
        )
        CircularProgressIndicator(
            modifier = Modifier.size(202.dp),
            progress = animateProgress,
            color = CheckColor.Primary100,
            strokeWidth = 8.dp,
        )
    }
}

internal fun getRank(currentReward: Int): Rank {
    return when (currentReward) {
        in Rank.HeartShake.minReward until Rank.HeartShake.maxReward -> Rank.HeartShake
        else -> Rank.LumbarDisk

    }
}

sealed class Rank(
    @DrawableRes val drawable: Int,
    val rank: String,
    val minReward: Int,
    val maxReward: Int,
) {
    object HeartShake : Rank(
        drawable = R.drawable.ic_check_logo,
        rank = "모두 다 달성하셨습니다!",
        minReward = 0,
        maxReward = 1500,
    )

    object LumbarDisk : Rank(
        drawable = R.drawable.ic_check_logo,
        rank = "조금만 더 노력하세요!",
        minReward = 1500,
        maxReward = 3400,
    )

    /*object Flu : Rank(
        drawable = R.drawable.ic_flu,
        rank = R.string.rank_flu,
        minReward = 3400,
        maxReward = 5700,
    )

    object Cold : Rank(
        drawable = R.drawable.ic_cold,
        rank = R.string.rank_cold,
        minReward = 5700,
        maxReward = 8400,
    )

    object Happy : Rank(
        drawable = R.drawable.ic_happy,
        rank = R.string.rank_happy,
        minReward = 8400,
        maxReward = 10000,
    )*/
}

private fun getMaxReward(
    rank: Rank,
): Int {

    val currentReward = mutableListOf(
        Rank.HeartShake.maxReward,
        /*Rank.LumbarDisk.maxReward,
        Rank.Flu.maxReward,
        Rank.Cold.maxReward,
        Rank.Happy.maxReward*/
    )

    val index = currentReward.indexOf(rank.maxReward)

    for (i in index until currentReward.size) {
        currentReward.removeAt(i)
    }

    return currentReward.sum()
}