package com.alyxe.compose.anims.otp.field

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.appkode.tbi.core.uikit.TbiTheme

@Composable
fun Shaker(
  modifier: Modifier = Modifier,
  animationSpec: AnimationSpec<Float> = spring(),
  shake: Boolean,
  onAnimationFinished: () -> Unit = {},
  content: @Composable () -> Unit,
) {
  var offset by remember {
    mutableStateOf(value = 0f)
  }

  LaunchedEffect(shake) {
    offset = 0f
    if (shake) {
      var revesed = false

      for (i in 0 until 6) {
        animate(
          initialValue = 0f,
          targetValue = 2f,
          animationSpec = animationSpec
        ) { value, _ ->
          if (revesed) {
            offset -= value
          } else {
            offset += value
          }
        }
        revesed = !revesed
      }
      delay(100)
      offset = 0f
      onAnimationFinished()
    }
  }

  Box(modifier = modifier.offset(x = offset.dp)) {
    content()
  }
}

@Composable
private fun Sample() {
  var animate by remember {
    mutableStateOf(value = false)
  }

  Box(
    modifier = Modifier
      .background(TbiTheme.colors.backgroundPrimary)
      .padding(40.dp)
  ) {
    Shaker(
      modifier = Modifier
        .clickable { animate = !animate },
      animationSpec = tween(durationMillis = 100, easing = LinearEasing),
      shake = animate,
    ) {
      Row {
        Box(
          modifier = Modifier
            .size(40.dp)
            .background(TbiTheme.colors.backgroundAccentPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
          modifier = Modifier
            .size(40.dp)
            .background(TbiTheme.colors.backgroundAccentPrimary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box(
          modifier = Modifier
            .size(40.dp)
            .background(TbiTheme.colors.backgroundAccentPrimary)
        )
      }
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
