package com.example.delivary_user_android.common.ui.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {
    data class DynamicString(val value: String) : UIText()
    class StringResource(@StringRes val id: Int) : UIText()
}

@Composable
fun UIText?.asString(): String {
    return when (this) {
        is UIText.DynamicString -> value
        is UIText.StringResource -> stringResource(id = id)
        else -> ""
    }
}