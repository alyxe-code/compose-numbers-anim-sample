package ru.appkode.tbi.core.uikit

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class TbiShapes(
  val small1: Shape,
  val medium1: Shape,
  val medium2: Shape,
  val medium3: Shape,
  val large1: Shape,
  val bottomSheet: Shape,
  val roundBottom32: Shape,
  val roundBottom24: Shape,
)

val DefaultTbiShapes = TbiShapes(
  small1 = RoundedCornerShape(12.dp),
  medium1 = RoundedCornerShape(16.dp),
  medium2 = RoundedCornerShape(18.dp),
  medium3 = RoundedCornerShape(24.dp),
  large1 = RoundedCornerShape(32.dp),
  bottomSheet = RoundedCornerShape(
    topStart = 24.dp,
    topEnd = 24.dp
  ),
  roundBottom32 = RoundedCornerShape(
    bottomStart = 32.dp,
    bottomEnd = 32.dp,
  ),
  roundBottom24 = RoundedCornerShape(
    bottomStart = 24.dp,
    bottomEnd = 24.dp,
  ),
)
