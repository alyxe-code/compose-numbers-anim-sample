package com.alyxe.compose.anims.otp.field

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alyxe.compose.anims.otp.OtpCodeRunner
import com.alyxe.compose.anims.otp.OtpCodeState
import ru.appkode.tbi.core.uikit.TbiTheme

@ExperimentalAnimationApi
@Composable
fun OtpCodeField(
  modifier: Modifier = Modifier,
  state: OtpCodeState,
  onAnimationFinished: (OtpCodeState) -> Unit,
) {
  Box(
    modifier = modifier.padding(horizontal = 16.dp),
    contentAlignment = Alignment.CenterStart,
  ) {
    Shaker(
      shake = state is OtpCodeState.Failure,
      onAnimationFinished = { onAnimationFinished(state) },
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
      ) {
        for (index in 0 until 6) {
          if (index != 0) {
            Spacer(modifier = Modifier.width(8.dp))
          }

          val code = if (state is OtpCodeState.EnterCode) {
            state.code.getOrNull(index)
          } else {
            null
          }

          CollapsableOtpCodeCell(
            value = code,
            expanded = state is OtpCodeState.EnterCode,
            onAnimationFinished = { onAnimationFinished(state) },
          )
        }
      }
    }

    if (state is OtpCodeState.InProgress.CollapsedWithRunner) {
      OtpCodeRunner(
        position = state.position,
        onPositionChanged = { onAnimationFinished(state) },
      )
    }
  }
}

@ExperimentalAnimationApi
@Composable
private fun Sample() {
  var state: OtpCodeState by remember {
    mutableStateOf(
      value = OtpCodeState.EnterCode(
        code = "1234"
      )
    )
  }

  Column(
    modifier = Modifier
      .background(TbiTheme.colors.backgroundPrimary)
      .padding(8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    OtpCodeField(
      modifier = Modifier.padding(12.dp),
      state = state,
      onAnimationFinished = {
        when (state) {
          is OtpCodeState.EnterCode -> Unit
          is OtpCodeState.InProgress.Collapse,
          is OtpCodeState.InProgress.CollapsedWithRunner -> {
            state = state.updateState()
          }
        }
      },
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      modifier = Modifier.clickable {
        state = state.updateState()
      },
      text = "next state",
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = state.toString(),
    )
  }
}

private fun OtpCodeState.updateState(): OtpCodeState {
  return when (this) {
    is OtpCodeState.EnterCode -> {
      if (this.code.length < 6) {
        OtpCodeState.EnterCode(code = "123456")
      } else {
        OtpCodeState.InProgress.Collapse
      }
    }
    is OtpCodeState.InProgress.Collapse -> OtpCodeState.InProgress.CollapsedWithRunner(
      position = 0,
      reversed = false,
    )
    is OtpCodeState.InProgress.CollapsedWithRunner -> {
      when {
        reversed -> when (position) {
          0 -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = 0,
            reversed = false,
          )
          else -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = position - 1,
            reversed = true,
          )
        }
        else -> when (position) {
          5 -> {
            OtpCodeState.InProgress.CollapsedWithRunner(
              position = position - 1,
              reversed = true,
            )
            OtpCodeState.Failure
          }
          else -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = position + 1,
            reversed = false,
          )
        }
      }
    }
    is OtpCodeState.Failure -> OtpCodeState.EnterCode(code = "")
    is OtpCodeState.Success -> when (this) {
      OtpCodeState.Success.Shrink -> OtpCodeState.Success.Finish
      OtpCodeState.Success.Finish -> OtpCodeState.EnterCode(code = "")
    }
  }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun PreviewLight() {
  TbiTheme(useDarkTheme = false) {
    Sample()
  }
}
