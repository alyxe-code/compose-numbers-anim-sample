package com.alyxe.compose.anims.otp.field

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.appkode.tbi.core.uikit.TbiTheme

@Immutable
internal data class CellProperties(
  val width: Dp,
  val height: Dp,
  val cornerRadius: Dp,
  val fontSize: TextUnit,
  val textAlpha: Float,
)

@Composable
internal fun expandedCellProperties(): CellProperties {
  return CellProperties(
    width = 40.dp,
    height = 48.dp,
    cornerRadius = 12.dp,
    fontSize = TbiTheme.typography.title2.fontSize,
    textAlpha = 1f,
  )
}

@Composable
internal fun collapsedCellProperties(): CellProperties {
  return CellProperties(
    width = 14.dp,
    height = 14.dp,
    cornerRadius = 4.dp,
    fontSize = 0.sp,
    textAlpha = 0f,
  )
}
