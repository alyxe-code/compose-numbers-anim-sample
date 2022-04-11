package ru.appkode.tbi.core.uikit

import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.alyxe.compose.anims.R

@Composable
@ReadOnlyComposable
private fun lightColorPaletteResources(): TbiColors {
  return TbiColors(
    backgroundPrimary = colorResource(R.color.gray0),
    brandPrimary = colorResource(R.color.orange150),
    brandSecondary = colorResource(R.color.gray450),
    backgroundSecondary = colorResource(R.color.gray100),
    backgroundAccentPrimary = colorResource(R.color.orange0),
    backgroundAccentSecondary = colorResource(R.color.gray550),
    cardPrimary = colorResource(R.color.gray0),
    cardSecondary = colorResource(R.color.gray100),
    cardTertiary = colorResource(R.color.gray0),
    cardQuaternary = colorResource(R.color.gray200),
    cardAccent = colorResource(R.color.gray700),
    contentPrimary = colorResource(R.color.gray800),
    contentSecondary = colorResource(R.color.gray300),
    contentTertiary = colorResource(R.color.gray250),
    contentQuaternary = colorResource(R.color.gray200),
    contentAccentPrimary = colorResource(R.color.orange400),
    contentAccentSecondary = colorResource(R.color.gray0),
    contentAccentVariant = colorResource(R.color.gray0),
    errorPrimary = colorResource(R.color.red100),
    errorSecondary = colorResource(R.color.red0),
    successPrimary = colorResource(R.color.green200),
    successSecondary = colorResource(R.color.green0),
    buttonPrimaryBg = colorResource(R.color.orange400),
    buttonPrimaryVariantBg = colorResource(R.color.gray700),
    buttonPrimaryContent = colorResource(R.color.gray0),
    buttonPrimaryVariantContent = colorResource(R.color.gray0),
    buttonPrimaryBgDisabled = colorResource(R.color.gray150),
    buttonPrimaryVariantBgDisabled = colorResource(R.color.gray150),
    buttonPrimaryContentDisabled = colorResource(R.color.gray0),
    buttonPrimaryVariantContentDisabled = colorResource(R.color.gray0),
    buttonSecondaryBg = colorResource(R.color.gray100),
    buttonSecondaryBgDisabled = colorResource(R.color.gray100),
    buttonSecondaryContent = colorResource(R.color.orange300),
    buttonSecondaryContentDisabled = colorResource(R.color.gray200),
    buttonTertiaryContent = colorResource(id = R.color.orange300),
    buttonTertiaryContentDisabled = colorResource(id = R.color.gray200),
    shimmerColor = colorResource(id = R.color.gray100),
    shimmerHighlightColor = colorResource(id = R.color.gray0),
  )
}

@Composable
@ReadOnlyComposable
private fun darkColorPaletteResources(): TbiColors {
  return TbiColors(
    backgroundPrimary = colorResource(R.color.gray900),
    brandPrimary = colorResource(R.color.orange150),
    brandSecondary = colorResource(R.color.gray0),
    backgroundSecondary = colorResource(R.color.gray700),
    backgroundAccentPrimary = colorResource(R.color.orange900),
    backgroundAccentSecondary = colorResource(R.color.gray550),
    cardPrimary = colorResource(R.color.gray500),
    cardSecondary = colorResource(R.color.gray550),
    cardTertiary = colorResource(R.color.gray550),
    cardQuaternary = colorResource(R.color.gray250),
    cardAccent = colorResource(R.color.gray500),
    contentPrimary = colorResource(R.color.gray0),
    contentSecondary = colorResource(R.color.gray350),
    contentTertiary = colorResource(R.color.gray300),
    contentQuaternary = colorResource(R.color.gray250),
    contentAccentPrimary = colorResource(R.color.orange200),
    contentAccentSecondary = colorResource(R.color.gray0),
    contentAccentVariant = colorResource(R.color.gray800),
    errorPrimary = colorResource(R.color.red200),
    errorSecondary = colorResource(R.color.red900),
    successPrimary = colorResource(R.color.green100),
    successSecondary = colorResource(R.color.green900),
    buttonPrimaryBg = colorResource(R.color.orange200),
    buttonPrimaryVariantBg = colorResource(R.color.orange100),
    buttonPrimaryContent = colorResource(R.color.gray0),
    buttonPrimaryVariantContent = colorResource(R.color.gray0),
    buttonPrimaryBgDisabled = colorResource(R.color.gray400),
    buttonPrimaryVariantBgDisabled = colorResource(R.color.gray150),
    buttonPrimaryContentDisabled = colorResource(R.color.gray0),
    buttonPrimaryVariantContentDisabled = colorResource(R.color.gray0),
    buttonSecondaryBg = colorResource(R.color.gray550),
    buttonSecondaryBgDisabled = colorResource(R.color.gray550),
    buttonSecondaryContent = colorResource(R.color.orange100),
    buttonSecondaryContentDisabled = colorResource(R.color.gray400),
    buttonTertiaryContent = colorResource(id = R.color.orange100),
    buttonTertiaryContentDisabled = colorResource(id = R.color.gray450),
    shimmerColor = colorResource(id = R.color.gray900),
    shimmerHighlightColor = colorResource(id = R.color.gray500),
  )
}

@Composable
fun TbiTheme(
  useDarkTheme: Boolean = false,
  typography: TbiTypography = TbiTheme.typography,
  content: @Composable () -> Unit,
) {
  val colors = if (useDarkTheme) {
    darkColorPaletteResources()
  } else {
    lightColorPaletteResources()
  }

  val colorPalette = remember { colors }
  colorPalette.update(colors)

  val shapes = remember { DefaultTbiShapes }

  MaterialTheme(
    colors = debugColors(),
    typography = MaterialTypography,
  ) {
    CompositionLocalProvider(
      LocalTbiColors provides colorPalette,
      LocalTbiTypography provides typography,
      LocalContentColor provides colors.contentPrimary,
      LocalTbiShapes provides shapes,
      content = content,
    )
  }
}

object TbiTheme {
  val colors: TbiColors
    @Composable
    @ReadOnlyComposable
    get() = LocalTbiColors.current

  val typography: TbiTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTbiTypography.current

  val shapes: TbiShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalTbiShapes.current
}

@Suppress("LongParameterList") // contains whole palette
@Stable
class TbiColors(
  backgroundPrimary: Color,
  brandPrimary: Color,
  brandSecondary: Color,
  backgroundSecondary: Color,
  backgroundAccentPrimary: Color,
  backgroundAccentSecondary: Color,
  cardPrimary: Color,
  cardSecondary: Color,
  cardTertiary: Color,
  cardQuaternary: Color,
  cardAccent: Color,
  contentPrimary: Color,
  contentSecondary: Color,
  contentTertiary: Color,
  contentQuaternary: Color,
  contentAccentPrimary: Color,
  contentAccentSecondary: Color,
  contentAccentVariant: Color,
  errorPrimary: Color,
  errorSecondary: Color,
  successPrimary: Color,
  successSecondary: Color,
  buttonPrimaryBg: Color,
  buttonPrimaryContent: Color,
  buttonPrimaryVariantContent: Color,
  buttonPrimaryBgDisabled: Color,
  buttonPrimaryVariantBgDisabled: Color,
  buttonPrimaryContentDisabled: Color,
  buttonPrimaryVariantContentDisabled: Color,
  buttonSecondaryBg: Color,
  buttonPrimaryVariantBg: Color,
  buttonSecondaryContent: Color,
  buttonSecondaryBgDisabled: Color,
  buttonSecondaryContentDisabled: Color,
  buttonTertiaryContent: Color,
  buttonTertiaryContentDisabled: Color,
  shimmerColor: Color,
  shimmerHighlightColor: Color,
) {
  var backgroundPrimary by mutableStateOf(backgroundPrimary)
    private set
  var brandPrimary by mutableStateOf(brandPrimary)
    private set
  var brandSecondary by mutableStateOf(brandSecondary)
    private set
  var backgroundSecondary by mutableStateOf(backgroundSecondary)
    private set
  var backgroundAccentPrimary by mutableStateOf(backgroundAccentPrimary)
    private set
  var backgroundAccentSecondary by mutableStateOf(backgroundAccentSecondary)
    private set
  var cardPrimary by mutableStateOf(cardPrimary)
    private set
  var cardSecondary by mutableStateOf(cardSecondary)
    private set
  var cardTertiary by mutableStateOf(cardTertiary)
    private set
  var cardQuaternary by mutableStateOf(cardQuaternary)
    private set
  var cardAccent by mutableStateOf(cardAccent)
    private set
  var contentPrimary by mutableStateOf(contentPrimary)
    private set
  var contentSecondary by mutableStateOf(contentSecondary)
    private set
  var contentTertiary by mutableStateOf(contentTertiary)
    private set
  var contentQuaternary by mutableStateOf(contentQuaternary)
    private set
  var contentAccentPrimary by mutableStateOf(contentAccentPrimary)
    private set
  var contentAccentSecondary by mutableStateOf(contentAccentSecondary)
    private set
  var contentAccentVariant by mutableStateOf(contentAccentVariant)
    private set
  var errorPrimary by mutableStateOf(errorPrimary)
    private set
  var errorSecondary by mutableStateOf(errorSecondary)
    private set
  var successPrimary by mutableStateOf(successPrimary)
    private set
  var successSecondary by mutableStateOf(successSecondary)
    private set
  var buttonPrimaryBg by mutableStateOf(buttonPrimaryBg)
    private set
  var buttonPrimaryVariantBg by mutableStateOf(buttonPrimaryVariantBg)
    private set
  var buttonPrimaryContent by mutableStateOf(buttonPrimaryContent)
    private set
  var buttonPrimaryVariantContent by mutableStateOf(buttonPrimaryVariantContent)
    private set
  var buttonPrimaryBgDisabled by mutableStateOf(buttonPrimaryBgDisabled)
    private set
  var buttonPrimaryVariantBgDisabled by mutableStateOf(buttonPrimaryVariantBgDisabled)
    private set
  var buttonPrimaryContentDisabled by mutableStateOf(buttonPrimaryContentDisabled)
    private set
  var buttonPrimaryVariantContentDisabled by mutableStateOf(buttonPrimaryVariantContentDisabled)
    private set
  var buttonSecondaryBg: Color by mutableStateOf(buttonSecondaryBg)
    private set
  var buttonSecondaryContent by mutableStateOf(buttonSecondaryContent)
    private set
  var buttonSecondaryBgDisabled by mutableStateOf(buttonSecondaryBgDisabled)
    private set
  var buttonSecondaryContentDisabled by mutableStateOf(buttonSecondaryContentDisabled)
    private set
  var buttonTertiaryContent by mutableStateOf(buttonTertiaryContent)
    private set
  var buttonTertiaryContentDisabled by mutableStateOf(buttonTertiaryContentDisabled)
    private set
  var shimmerColor by mutableStateOf(shimmerColor)
    private set
  var shimmerHighlightColor by mutableStateOf(shimmerHighlightColor)
    private set

  fun update(other: TbiColors) {
    backgroundPrimary = other.backgroundPrimary
    brandPrimary = other.brandPrimary
    brandSecondary = other.brandSecondary
    backgroundSecondary = other.backgroundSecondary
    backgroundAccentPrimary = other.backgroundAccentPrimary
    backgroundAccentSecondary = other.backgroundAccentSecondary
    cardPrimary = other.cardPrimary
    cardSecondary = other.cardSecondary
    cardTertiary = other.cardTertiary
    cardAccent = other.cardAccent
    contentPrimary = other.contentPrimary
    contentSecondary = other.contentSecondary
    contentTertiary = other.contentTertiary
    contentQuaternary = other.contentQuaternary
    contentAccentPrimary = other.contentAccentPrimary
    contentAccentSecondary = other.contentAccentSecondary
    contentAccentVariant = other.contentAccentVariant
    errorPrimary = other.errorPrimary
    errorSecondary = other.errorSecondary
    successPrimary = other.successPrimary
    successSecondary = other.successSecondary
    buttonPrimaryBg = other.buttonPrimaryBg
    buttonPrimaryVariantBg = other.buttonPrimaryVariantBg
    buttonPrimaryContent = other.buttonPrimaryContent
    buttonPrimaryVariantContent = other.buttonPrimaryVariantContent
    buttonPrimaryBgDisabled = other.buttonPrimaryBgDisabled
    buttonPrimaryContentDisabled = other.buttonPrimaryContentDisabled
    buttonPrimaryVariantContentDisabled = other.buttonPrimaryVariantContentDisabled
    buttonSecondaryBg = other.buttonSecondaryBg
    buttonSecondaryBgDisabled = other.buttonSecondaryBgDisabled
    buttonSecondaryContent = other.buttonSecondaryContent
    buttonSecondaryContentDisabled = other.buttonSecondaryContentDisabled
    buttonTertiaryContent = other.buttonSecondaryContent
    buttonTertiaryContentDisabled = other.buttonTertiaryContentDisabled
    shimmerColor = other.shimmerColor
    shimmerHighlightColor = other.shimmerHighlightColor
  }
}

private val LocalTbiColors = staticCompositionLocalOf<TbiColors> {
  error("No TbiColors provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [.colors].
 */
fun debugColors() = Colors(
  primary = DebugColor,
  primaryVariant = DebugColor,
  secondary = DebugColor,
  secondaryVariant = DebugColor,
  background = DebugColor,
  surface = DebugColor,
  error = DebugColor,
  onPrimary = DebugColor,
  onSecondary = DebugColor,
  onBackground = DebugColor,
  onSurface = DebugColor,
  onError = DebugColor,
  isLight = true
)

private val DebugColor = Color.Magenta

private val LocalTbiShapes = staticCompositionLocalOf<TbiShapes> { error("No TbiShapes provided") }
