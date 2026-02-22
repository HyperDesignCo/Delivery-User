package com.example.delivaryUser.common.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalDelivaryUserColors = staticCompositionLocalOf<DelivaryUserColors> { error("Cannot provide colors") }

data class DelivaryUserColors(
    val primary: Color,
    val secondary: Color,
    val secondaryGray : Color,
    val primaryVariant: Color,
    val secondaryVariant: Color,
    val shadow: Color,
    val background: BackgroundColors,
    val status: StatusColors,
)

data class BackgroundColors(
    val surface: Color,
    val background: Color,
    val surfaceHigh: Color,
    val hint: Color,
    val stroke: Color,
    val disable: Color,
    val stoke: Color,
)

data class StatusColors(
    val redAccent: Color,
    val yellowAccent: Color,
    val greenAccent: Color,
    val blueAccent: Color,
    val grayAccent: Color,
    val darkGreen: Color,
    val orangeAccent: Color,
    val accentColor: Color,
    val darkBlueAccent: Color,
)

val backgroundLightColors = BackgroundColors(
    surface = Color(0XFFEEEEEE),
    background = Color(0XFFFFFFFF),
    surfaceHigh = Color(0XFFFBFBFB),
    hint = Color(0x804E4E4E),
    disable = Color(0xFFE1E4E5),
    stoke = Color(0x8046200B),
    stroke = Color(0XFFD9D9D9)
)
val statusLightColors = StatusColors(
    redAccent = Color(0XFFFF4949),
    yellowAccent = Color(0XFFFFCC3D),
    greenAccent = Color(0XFF00E264),
    blueAccent = Color(0XFFC2FFD2),
    grayAccent = Color(0x1A0973BA),
    orangeAccent = Color(0XFFFFF5BC),
    accentColor = Color(0xFFFEB249),
    darkGreen = Color(0xFF309449),
    darkBlueAccent = Color(0xFF0095DF)
)
val delivaryUserLightColors = DelivaryUserColors(
    primary = Color(0XFFE41F28),
    secondary = Color(0XFF4E4E4E),
    primaryVariant = Color(0XFFEE1E25),
    background = backgroundLightColors,
    status = statusLightColors,
    shadow = Color(0x0D000000),
    secondaryVariant = Color(0xB34E4E4E),
    secondaryGray =  Color(0x1A4E4E4E)
)

val backgroundDarkColors = BackgroundColors(
    surface = Color(0XFF1E1E1E),
    surfaceHigh = Color(0XFF2C2C2C),
    hint = Color(0x80B0B0B0),
    disable = Color(0xFF292828),
    stoke = Color(0x8046200B),
    background = Color(0XFFFFFFFF),
    stroke = Color(0XFF2D2D2D)
)
val statusDarkColors = StatusColors(
    redAccent = Color(0XFFFF5252),
    yellowAccent = Color(0XFFFFD54F),
    greenAccent = Color(0XFF00E676),
    blueAccent = Color(0XFF69F0AE),
    grayAccent = Color(0XFF42A5F5),
    orangeAccent = Color(0XFFFFD088),
    accentColor = Color(0xFFFEB249),
    darkGreen = Color(0xFF309449),
    darkBlueAccent = Color(0xFF0095DF)
)
val delivaryUserDarkColors = DelivaryUserColors(
    primary = Color(0XFFFF3B43),
    secondary = Color(0XFFE0E0E0),
    primaryVariant = Color(0XFFE0E0E0),
    background = backgroundDarkColors,
    status = statusDarkColors,
    shadow = Color(0x0D000000),
    secondaryVariant = Color(0XFFE0E0E0),
    secondaryGray =  Color(0x1A4E4E4E)
)