package com.hyperdesign.delivaryUser.common.ui.components.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

private const val FONT_SCALE = "1. Font Scale"
private const val DEVICE = "2. Device"
private const val THEME = "3. Theme"
private const val LAYOUT_DIRECTION = "4. Layout Direction"

/**
 * MultiPreview annotation to render the composable in extra small and extra large font sizes
 * for accessibility and responsive typography testing.
 *
 * - Small font scale: 0.5x
 * - Default font scale: 1.0x
 * - Large font scale: 1.5x
 * - XLarge font scale: 2.0x
 *
 * Use this to ensure your composable remains legible and visually balanced across font scale changes.
 */

@Preview(name = "A. XSmall (0.85x)", group = FONT_SCALE, fontScale = 0.85f)
@Preview(name = "B. Small (0.95x)", group = FONT_SCALE, fontScale = 0.95f)
@Preview(name = "C. Default (1.00x)", group = FONT_SCALE, fontScale = 1.0f)
@Preview(name = "D. Large (1.15x)", group = FONT_SCALE, fontScale = 1.15f)
@Preview(name = "E. XLarge (1.30x)", group = FONT_SCALE, fontScale = 1.30f)
@Preview(name = "F. XXLarge (1.50x)", group = FONT_SCALE, fontScale = 1.50f)
annotation class FontScalePreviews


/**
 * MultiPreview annotation to render the composable across common device form factors:
 *
 * - Phone
 * - Foldable
 * - Tablet
 *
 * Use this to ensure your composable adapts well across different screen sizes and DPIs.
 */

@Preview(name = "G. Phone", group = DEVICE, device = "spec:width=360dp,height=640dp,dpi=480")
@Preview(name = "H. Foldable", group = DEVICE, device = "spec:width=673dp,height=841dp,dpi=480")
@Preview(name = "I. Tablet", group = DEVICE, device = "spec:width=1280dp,height=800dp,dpi=480")
annotation class DevicePreviews


/**
 * MultiPreview annotation to render the composable in both Light and Dark themes.
 *
 * This allows you to ensure your composable adapts well to both theme variants.
 */

@Preview(name = "J. Dark Theme", group = THEME, uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Preview(name = "K. Light Theme", group = THEME, uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL)
annotation class ThemePreviews


/**
 * MultiPreview annotation to render the composable in both LTR and RTL layout directions.
 *
 * - LTR with English locale.
 * - RTL with Arabic locale.
 *
 * Use this to ensure your composable correctly mirrors layout and aligns content for RTL languages.
 */

@Preview(name = "L. LTR", group = LAYOUT_DIRECTION, locale = "en")
@Preview(name = "M. RTL", group = LAYOUT_DIRECTION, locale = "ar")
annotation class LayoutDirectionPreviews

/**
 * Add this MultiPreview annotation to a composable to render the composable in various common
 * configurations:
 *
 * - Font Scales
 * - Small, Default, Large and XLarge font sizes
 * - Dark and Light Themes
 * - Various Device Sizes
 *
 * _Note: Combining MultiPreview annotations doesn't mean all the different combinations are shown.
 * Instead, each MultiPreview annotation acts by its own and renders only its own variants._
 */

@FontScalePreviews
@DevicePreviews
@ThemePreviews
@LayoutDirectionPreviews
annotation class PreviewAllVariants