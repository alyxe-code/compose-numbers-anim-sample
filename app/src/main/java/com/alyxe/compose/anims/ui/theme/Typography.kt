package ru.appkode.tbi.core.uikit

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.alyxe.compose.anims.R

private val Inter = FontFamily(
  Font(R.font.inter_extra_bold_family, FontWeight.ExtraBold),
  Font(R.font.inter_semi_bold_family, FontWeight.SemiBold),
  Font(R.font.inter_bold_family, FontWeight.Bold),
  Font(R.font.inter_medium_family, FontWeight.Medium),
  Font(R.font.inter_regular_family, FontWeight.Normal)
)

@Immutable
data class TbiTypography internal constructor(
  val header1: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 56.sp
  ),
  val header2: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 32.sp
  ),
  val header3: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Bold,
    fontSize = 32.sp
  ),
  val title1: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
  ),
  val title2: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp
  ),
  val title3: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp
  ),
  val subtitle: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp
  ),
  val headline: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp
  ),
  val button: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp
  ),
  val body1: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Bold,
    fontSize = 17.sp
  ),
  val body2: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),
  val body3: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp
  ),
  val caption1: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp
  ),
  val caption2: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    fontSize = 11.sp
  ),
  val caption3: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 9.sp
  ),
  val tab: TextStyle = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp
  )
)

/**
 * Used for fallback only and shouldn't be used generally, use [TbiTypography] instead.
 */
val MaterialTypography = Typography()

internal val LocalTbiTypography = staticCompositionLocalOf { TbiTypography() }
