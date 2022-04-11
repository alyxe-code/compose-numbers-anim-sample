package com.alyxe.compose.anims.otp.success

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.appkode.tbi.core.uikit.TbiTheme

@Composable
fun AnimatedScale(
  modifier: Modifier = Modifier,
  scaleList: List<Float>,
  animationSpec: AnimationSpec<Float> = spring(),
  animate: Boolean,
  onAnimationFinished: () -> Unit = {},
  content: @Composable () -> Unit,
) {
  var currentScale by remember { mutableStateOf(scaleList.first()) }

  LaunchedEffect(scaleList, animate) {
    if (animate && scaleList.size > 1) {
      var previous = scaleList.first()
      for (index in 1 until scaleList.size) {
        val new = scaleList[index]
        animate(
          initialValue = previous,
          targetValue = new,
          animationSpec = animationSpec,
        ) { value, _ ->
          currentScale = value
        }
        previous = scaleList[index]
      }
      onAnimationFinished()
    }
  }

  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center,
  ) {
    Box(
      modifier = Modifier.scale(currentScale),
      contentAlignment = Alignment.Center,
    ) {
      content()
    }
  }
}

@Composable
private fun Sample() {
  Box(
    modifier = Modifier
      .background(TbiTheme.colors.backgroundPrimary)
      .padding(40.dp)
  ) {
    AnimatedScale(
      scaleList = listOf(0.0f, 1.05f, 0.95f, 1.0f),
      animationSpec = tween(easing = LinearEasing),
      animate = true,
    ) {
      Box(
        modifier = Modifier
          .size(200.dp)
          .background(
            color = TbiTheme.colors.backgroundAccentPrimary,
            shape = RoundedCornerShape(100.dp)
          )
      )
    }
  }
}

@Preview
@Composable
private fun PreviewLight() {
  TbiTheme(useDarkTheme = false) {
    Sample()
  }
}

@Preview
@Composable
private fun PreviewDark() {
  TbiTheme(useDarkTheme = true) {
    Sample()
  }
}
