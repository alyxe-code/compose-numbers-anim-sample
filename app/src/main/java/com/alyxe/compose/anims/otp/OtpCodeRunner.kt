package com.alyxe.compose.anims.otp

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.appkode.tbi.core.uikit.TbiTheme

@Composable
fun OtpCodeRunner(
  modifier: Modifier = Modifier,
  position: Int,
  onPositionChanged: () -> Unit,
) {
  LaunchedEffect(position) {
    if (position == 0) {
      onPositionChanged()
    }
  }

  var width by remember {
    mutableStateOf(value = RunnerMinWidth)
  }

  var cornerRadius by remember {
    mutableStateOf(value = RunnerMinCornerRadius)
  }

  LaunchedEffect(position) {
    animate(
      initialValue = 0f,
      targetValue = 1f,
    ) { value, _ ->
      val factor = if (value < 0.5f) {
        value * 2f
      } else {
        (1f - value) * 2f
      }

      width = RunnerMinWidth + RunnerMinWidth * factor
      cornerRadius = RunnerMinCornerRadius + RunnerMinCornerRadius * factor
    }
  }

  val offset by animateDpAsState(
    targetValue = calculateNewPosition(position, width),
    finishedListener = { onPositionChanged() }
  )

  OtpCodeRunnerContent(
    width = width,
    cornerRadius = cornerRadius,
    offsetStart = offset,
    modifier = modifier,
  )
}

private fun calculateNewPosition(position: Int, width: Dp): Dp {
  return ((CellContainerWidth + SpacerWidth) * position) +
    (CellContainerWidth / 2) -
    (width / 2)
}

@Composable
private fun OtpCodeRunnerContent(
  modifier: Modifier = Modifier,
  width: Dp,
  cornerRadius: Dp,
  offsetStart: Dp,
) {
  Box(
    modifier = modifier
      .offset(x = offsetStart)
      .width(width)
      .height(RunnerHeight)
      .background(
        color = TbiTheme.colors.contentAccentPrimary,
        shape = RoundedCornerShape(cornerRadius),
      )
  )
}

private val RunnerHeight = 14.dp
private val RunnerMinWidth = 14.dp
private val RunnerMinCornerRadius = 4.dp
