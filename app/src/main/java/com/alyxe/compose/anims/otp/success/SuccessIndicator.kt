package com.alyxe.compose.anims.otp.success

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alyxe.compose.anims.R
import ru.appkode.tbi.core.uikit.TbiTheme

@Composable
fun SuccessIndicator(
  modifier: Modifier = Modifier,
  onAnimationFinished: () -> Unit = {},
) {
  var animState by remember { mutableStateOf(IndicatorAnimState()) }

  fun onFinishWithUpdate(block: IndicatorAnimState.() -> IndicatorAnimState): () -> Unit {
    return {
      animState = block(animState)
      if (animState.firstCircleFinished &&
        animState.secondCircleFinished &&
        animState.thirdCircleFinished &&
        animState.indicatorFinished
      ) {
        onAnimationFinished()
      }
    }
  }

  Box(
    modifier = Modifier,
    contentAlignment = Alignment.Center,
  ) {
    Circle(
      alpha = 0.05f,
      size = 88.dp,
      borderWidth = 1.dp,
      durationMillis = 500,
      onAnimationFinished = onFinishWithUpdate { copy(firstCircleFinished = true) },
    )

    Circle(
      alpha = 0.08f,
      size = 84.dp,
      borderWidth = 1.dp,
      durationMillis = 450,
      onAnimationFinished = onFinishWithUpdate { copy(secondCircleFinished = true) },
    )

    Circle(
      alpha = 0.2f,
      size = 78.dp,
      borderWidth = 1.5.dp,
      durationMillis = 350,
      onAnimationFinished = onFinishWithUpdate { copy(thirdCircleFinished = true) },
    )

    AnimatedScale(
      modifier = modifier,
      scaleList = DefaultScaleList,
      animate = true,
      onAnimationFinished = onFinishWithUpdate { copy(indicatorFinished = true) },
    ) {
      Box(
        modifier = Modifier
          .size(72.dp)
          .border(
            width = 4.dp,
            brush = SolidColor(TbiTheme.colors.contentAccentPrimary),
            shape = CircleShape,
          )
          .background(
            color = TbiTheme.colors.backgroundPrimary,
            shape = CircleShape,
          ),
        contentAlignment = Alignment.Center,
      ) {
        Icon(
          modifier = Modifier.size(32.dp),
          painter = painterResource(R.drawable.ic_done_24),
          contentDescription = null,
          tint = TbiTheme.colors.contentPrimary
        )
      }
    }
  }
}

private data class IndicatorAnimState(
  val firstCircleFinished: Boolean = false,
  val secondCircleFinished: Boolean = false,
  val thirdCircleFinished: Boolean = false,
  val indicatorFinished: Boolean = false,
)

private val DefaultScaleList = listOf(0.0f, 1.05f, 0.95f, 1.0f)

@Composable
private fun Circle(
  modifier: Modifier = Modifier,
  alpha: Float,
  size: Dp,
  borderWidth: Dp,
  durationMillis: Int,
  onAnimationFinished: () -> Unit,
) {
  AnimatedScale(
    modifier = modifier,
    scaleList = DefaultScaleList,
    animate = true,
    animationSpec = tween(durationMillis = durationMillis),
    onAnimationFinished = onAnimationFinished,
  ) {
    Box(
      modifier = Modifier
        .size(size)
        .border(
          width = borderWidth,
          brush = SolidColor(TbiTheme.colors.contentAccentPrimary.copy(alpha = alpha)),
          shape = CircleShape,
        )
    )
  }
}

@Composable
private fun Sample() {
  Box(
    modifier = Modifier
      .background(TbiTheme.colors.backgroundPrimary)
      .padding(40.dp)
  ) {
    SuccessIndicator()
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
