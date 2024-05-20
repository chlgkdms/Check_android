package com.example.check.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

data class TextFieldColor(
    val titleColor: TitleColor,
    val outlineColor: TextFieldOutlineColor,
    val backgroundColor: TextFieldBackgroundColor,
    val descriptionColor: DescriptionColor,
)

data class TitleColor(
    val default: Color,
    val disabled: Color,
)

data class TextFieldOutlineColor(
    val default: Color,
    val disabled: Color,
    val focused: Color,
)

data class TextFieldBackgroundColor(
    val default: Color,
    val disabled: Color,
)

data class DescriptionColor(
    val default: Color,
    val disabled: Color,
)

object CheckTextFieldColor {

    @Stable
    val Default
        @Composable get() = TextFieldColor(
            titleColor = TitleColor(
                default = CheckColor.Gray500,
                disabled = CheckColor.Gray400,
            ),
            outlineColor = TextFieldOutlineColor(
                default = CheckColor.Gray400,
                disabled = CheckColor.Gray400,
                focused = CheckColor.Primary100,
            ),
            backgroundColor = TextFieldBackgroundColor(
                default = CheckColor.Transparent,
                disabled = CheckColor.Gray200,
            ),
            descriptionColor = DescriptionColor(
                default = CheckColor.Gray400,
                disabled = CheckColor.Gray400,
            ),
        )
}