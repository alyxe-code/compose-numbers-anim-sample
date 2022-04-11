package com.alyxe.compose.anims.otp.success

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alyxe.compose.anims.otp.CellContainerWidth
import com.alyxe.compose.anims.otp.SpacerWidth
import com.alyxe.compose.anims.otp.field.OtpCodeCell
import com.alyxe.compose.anims.otp.field.collapsedCellProperties
import ru.appkode.tbi.core.uikit.TbiTheme

@ExperimentalAnimationApi
@Composable
internal fun ShrinkableOtpCodeField(
  modifier: Modifier = Modifier,
  shrink: Boolean,
  onAnimationFinished: () -> Unit,
) {
  var doShrink by remember {
    mutableStateOf(value = false)
  }

  LaunchedEffect(shrink) {
    doShrink = shrink
  }

  Box(modifier = modifier) {
    for (i in 0 until 6) {
      Shrinkable(
        position = i,
        shrink = doShrink,
        onAnimationFinished = onAnimationFinished,
      ) {
        OtpCodeCell(
          value = '1',
          props = collapsedCellProperties(),
          backgroundColor = TbiTheme.colors.backgroundAccentPrimary,
        )
      }
    }
  }
}

@Composable
private fun Shrinkable(
  modifier: Modifier = Modifier,
  position: Int,
  shrink: Boolean,
  onAnimationFinished: () -> Unit,
  content: @Composable () -> Unit,
) {
  val offset by animateDpAsState(
    targetValue = if (shrink) 0.dp else computeOffset(position),
    finishedListener = { onAnimationFinished() }
  )

  Row(modifier = modifier) {
    Spacer(modifier = Modifier.width(offset))
    content()
  }
}

private fun computeOffset(position: Int): Dp {
  return (CellContainerWidth + SpacerWidth) * position
}

@ExperimentalAnimationApi
@Composable
private fun Sample() {
  var shrink by remember {
    mutableStateOf(value = false)
  }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .background(TbiTheme.colors.backgroundPrimary)
      .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    ShrinkableOtpCodeField(
      shrink = shrink,
      onAnimationFinished = { },
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
      text = "toggle",
      modifier = Modifier
        .fillMaxWidth()
        .clickable { shrink = !shrink }
        .padding(8.dp)
    )
  }
}

@Preview
@Composable
@ExperimentalAnimationApi
private fun PreviewLight() {
  TbiTheme(useDarkTheme = false) {
    Sample()
  }
}
