package com.alyxe.compose.anims.otp.field

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import ru.appkode.tbi.core.uikit.TbiTheme

@ExperimentalAnimationApi
@Composable
internal fun CollapsableOtpCodeCell(
  modifier: Modifier = Modifier,
  value: Char?,
  expanded: Boolean,
  onAnimationFinished: () -> Unit = {},
) {
  var animationState by remember {
    mutableStateOf(value = AnimState())
  }

  LaunchedEffect(expanded) {
    animationState = AnimState()
  }

  fun finishWithUpdate(block: AnimState.() -> AnimState) {
    animationState = block(animationState)
    if (
      animationState.widthChanged &&
      animationState.heightChanged &&
      animationState.cornerRadiusChanged &&
      animationState.fontSizeChanged &&
      animationState.textAlphaChanged
    ) {
      onAnimationFinished()
    }
  }

  val props = animateCellProperties(
    props = if (expanded) expandedCellProperties() else collapsedCellProperties(),
    updateState = { finishWithUpdate(it) }
  )

  val backgroundColor by animateColorAsState(
    targetValue = computeBackgroundColor(expanded),
    finishedListener = { finishWithUpdate { copy(backgroundColorChanged = true) } },
  )

  OtpCodeCell(
    modifier = modifier,
    value = value,
    backgroundColor = backgroundColor,
    props = props,
  )
}

@Composable
private fun computeBackgroundColor(expanded: Boolean): Color {
  return if (expanded) {
    TbiTheme.colors.backgroundSecondary
  } else {
    TbiTheme.colors.backgroundAccentPrimary
  }
}

@Composable
private fun animateCellProperties(
  props: CellProperties,
  updateState: (AnimState.() -> AnimState) -> Unit,
): CellProperties {
  val width by animateDpAsState(
    targetValue = props.width,
    finishedListener = { updateState { copy(widthChanged = true) } },
  )
  val height by animateDpAsState(
    targetValue = props.height,
    finishedListener = { updateState { copy(heightChanged = true) } },
  )
  val cornerRadius by animateDpAsState(
    targetValue = props.cornerRadius,
    finishedListener = { updateState { copy(cornerRadiusChanged = true) } },
  )
  val fontSize by animateFloatAsState(
    targetValue = props.fontSize.value,
    finishedListener = { updateState { copy(fontSizeChanged = true) } },
  )
  val textAlpha by animateFloatAsState(
    targetValue = props.textAlpha,
    finishedListener = { updateState { copy(textAlphaChanged = true) } },
  )

  return CellProperties(
    width = width,
    height = height,
    cornerRadius = cornerRadius,
    fontSize = fontSize.sp,
    textAlpha = textAlpha,
  )
}

@Immutable
private data class AnimState(
  val widthChanged: Boolean = false,
  val heightChanged: Boolean = false,
  val cornerRadiusChanged: Boolean = false,
  val fontSizeChanged: Boolean = false,
  val textAlphaChanged: Boolean = false,
  val backgroundColorChanged: Boolean = false,
)
