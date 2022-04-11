package com.alyxe.compose.anims.otp

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface OtpCodeState {
  @Immutable
  data class EnterCode(
    val code: String,
  ) : OtpCodeState

  sealed interface InProgress : OtpCodeState {
    @Immutable
    object Collapse : InProgress

    @Immutable
    data class CollapsedWithRunner(
      val position: Int,
      val reversed: Boolean,
    ) : InProgress
  }

  @Stable
  sealed interface Success : OtpCodeState {
    @Immutable
    object Shrink : Success

    @Immutable
    object Finish : Success
  }

  @Immutable
  object Failure : OtpCodeState
}