package com.alyxe.compose.anims.otp.field

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.alyxe.compose.anims.otp.CellContainerHeight
import com.alyxe.compose.anims.otp.CellContainerWidth
import ru.appkode.tbi.core.uikit.TbiTheme

@ExperimentalAnimationApi
@Composable
internal fun OtpCodeCell(
  modifier: Modifier = Modifier,
  props: CellProperties,
  value: Char?,
  backgroundColor: Color,
) {
  Box(
    modifier = modifier
      .width(CellContainerWidth)
      .height(CellContainerHeight),
    contentAlignment = Alignment.Center,
  ) {
    Box(
      modifier = Modifier
        .width(props.width)
        .height(props.height)
        .background(
          color = backgroundColor,
          shape = RoundedCornerShape(props.cornerRadius)
        ),
      contentAlignment = Alignment.Center,
    ) {
      if (props.textAlpha >= 0.1f) {
        Text(
          modifier = Modifier.alpha(props.textAlpha),
          text = value?.toString().orEmpty(),
          style = TbiTheme.typography.title2.copy(fontSize = props.fontSize),
          color = TbiTheme.colors.contentPrimary
        )
      }
    }
  }
}
