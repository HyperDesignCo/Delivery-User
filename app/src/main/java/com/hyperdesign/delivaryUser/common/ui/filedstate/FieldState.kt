package com.hyperdesign.delivaryUser.common.ui.filedstate

import com.hyperdesign.delivaryUser.common.ui.extension.UIText

interface FieldState {
    val value: String
    val error: UIText?
    fun isError(): Boolean = error != null
}

data class TextFieldState(
    override val value: String = "",
    override val error: UIText? = null,
) : FieldState