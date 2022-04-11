package com.alyxe.compose.anims

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alyxe.compose.anims.otp.field.OtpCodeField
import com.alyxe.compose.anims.otp.OtpCodeState
import com.alyxe.compose.anims.otp.success.ShrinkableOtpCodeField
import com.alyxe.compose.anims.otp.success.SuccessIndicator
import ru.appkode.tbi.core.uikit.TbiTheme

class MainActivity : ComponentActivity() {
  @ExperimentalAnimationApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TbiTheme(useDarkTheme = false) {
        var otpCode by remember {
          mutableStateOf(value = "")
        }

        var otpCodeAnimState: OtpCodeState by remember {
          mutableStateOf(value = OtpCodeState.EnterCode(code = otpCode))
        }

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(TbiTheme.colors.backgroundPrimary),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally,
        ) {
          Box(
            modifier = Modifier.padding(40.dp),
            contentAlignment = Alignment.Center,
          ) {
            when (val currentState = otpCodeAnimState) {
              is OtpCodeState.EnterCode,
              is OtpCodeState.InProgress.Collapse,
              is OtpCodeState.InProgress.CollapsedWithRunner,
              is OtpCodeState.Failure -> {
                OtpCodeField(
                  state = otpCodeAnimState,
                  onAnimationFinished = { finishedState ->
                    otpCodeAnimState = when (finishedState) {
                      is OtpCodeState.EnterCode -> when (otpCode.length) {
                        6 -> OtpCodeState.InProgress.Collapse
                        else -> OtpCodeState.EnterCode(otpCode)
                      }
                      is OtpCodeState.InProgress.Collapse -> computeRunnerState(finishedState)
                      is OtpCodeState.InProgress.CollapsedWithRunner -> computeRunnerState(
                        finishedState
                      )
                      is OtpCodeState.Failure -> {
                        otpCode = ""
                        OtpCodeState.EnterCode(otpCode)
                      }
                      is OtpCodeState.Success -> when (finishedState) {
                        is OtpCodeState.Success.Shrink -> OtpCodeState.Success.Finish
                        is OtpCodeState.Success.Finish -> {
                          otpCode = ""
                          OtpCodeState.EnterCode(otpCode)
                        }
                      }
                    }
                  }
                )
              }
              is OtpCodeState.Success -> {
                when (currentState) {
                  OtpCodeState.Success.Shrink -> {
                    ShrinkableOtpCodeField(shrink = true) {
                      otpCodeAnimState = OtpCodeState.Success.Finish
                    }
                  }
                  OtpCodeState.Success.Finish -> SuccessIndicator {
                    otpCode = ""
                    otpCodeAnimState = OtpCodeState.EnterCode(otpCode)
                  }
                }
              }
            }
          }
          Spacer(modifier = Modifier.height(16.dp))
          Text(
            modifier = Modifier
              .fillMaxWidth()
              .clickable {
                val newState: OtpCodeState = when (val current = otpCodeAnimState) {
                  is OtpCodeState.EnterCode -> {
                    if (otpCode.length < 6) {
                      otpCode += "1"
                      OtpCodeState.EnterCode(otpCode)
                    } else {
                      OtpCodeState.InProgress.Collapse
                    }
                  }
                  is OtpCodeState.InProgress.Collapse -> computeRunnerState(otpCodeAnimState)
                  is OtpCodeState.InProgress.CollapsedWithRunner -> OtpCodeState.Success.Shrink
                  is OtpCodeState.Failure -> {
                    otpCode = ""
                    OtpCodeState.EnterCode(otpCode)
                  }
                  is OtpCodeState.Success -> when (current) {
                    OtpCodeState.Success.Shrink -> OtpCodeState.Success.Finish
                    OtpCodeState.Success.Finish -> {
                      otpCode = ""
                      OtpCodeState.EnterCode(otpCode)
                    }
                  }
                }

                otpCodeAnimState = newState
              }
              .padding(16.dp),
            text = "next state",
          )
        }
      }
    }
  }

  private fun computeRunnerState(previousState: OtpCodeState): OtpCodeState {
    return when (previousState) {
      is OtpCodeState.InProgress.CollapsedWithRunner -> when {
        previousState.reversed -> when (previousState.position) {
          0 -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = 0,
            reversed = false,
          )
          else -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = previousState.position - 1,
            reversed = true,
          )
        }
        else -> when (previousState.position) {
          5 -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = previousState.position - 1,
            reversed = true,
          )
          else -> OtpCodeState.InProgress.CollapsedWithRunner(
            position = previousState.position + 1,
            reversed = false,
          )
        }
      }
      else -> OtpCodeState.InProgress.CollapsedWithRunner(position = 0, reversed = false)
    }
  }
}
