package com.example.delivary_user_android.common.ui.filedstate

import com.example.delivary_user_android.common.ui.extension.UIText

interface FieldState {
    val value: String
    val error: UIText?
    fun isError(): Boolean = error != null
}