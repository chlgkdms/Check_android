import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.check.R
import com.example.check.designsystem.theme.CheckColor

@Stable
val maxLine = 1000

@Stable
val pretendardFamily = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
)

object CheckTypography {

    private val platFormTextStyle = PlatformTextStyle(
        includeFontPadding = false,
    )

    @Stable
    val titleLarge2 = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 92.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val titleLarge = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 52.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val title = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val subTitle = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val bodyLarge2 = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val bodyLarge = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val bodyStrong = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val body2 = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = platFormTextStyle,
    )

    @Stable
    val body = TextStyle(
        fontFamily = pretendardFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = platFormTextStyle,
    )
}

@Composable
fun TitleLarge2(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.titleLarge2,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun TitleLarge(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.titleLarge,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.title,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun SubTitle(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.subTitle,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun BodyLarge2(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.bodyLarge2,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun BodyLarge(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.bodyLarge,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun BodyStrong(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.bodyStrong,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun Body2(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.body2,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    color: Color = CheckColor.Gray700,
    text: String,
    decoration: TextDecoration = TextDecoration.None,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int = maxLine,
) {
    Text(
        modifier = modifier,
        color = color,
        text = text,
        fontFamily = pretendardFamily,
        style = CheckTypography.body,
        textDecoration = decoration,
        overflow = overflow,
        maxLines = maxLines,
    )
}