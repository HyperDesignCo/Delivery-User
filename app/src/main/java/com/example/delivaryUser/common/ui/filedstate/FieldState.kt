package com.example.delivaryUser.common.ui.filedstate

import com.example.delivaryUser.common.ui.extension.UIText

interface FieldState {
    val value: String
    val error: UIText?
    fun isError(): Boolean = error != null
}