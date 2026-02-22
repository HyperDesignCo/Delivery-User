package com.hyperdesign.delivaryUser.common.ui.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd . h:mma", Locale.ENGLISH)
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it).lowercase() } ?: this
    } catch (e: Exception) {
        this
    }
}