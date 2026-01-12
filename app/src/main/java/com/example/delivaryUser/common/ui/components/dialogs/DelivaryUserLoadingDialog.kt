package com.example.delivaryUser.common.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserLoadingDialog(modifier: Modifier = Modifier) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = modifier
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh, shape = RoundedCornerShape(24.dp))
                .padding(50.dp)
        ) {
            CircularProgressIndicator(color = DelivaryUserTheme.colors.primary)
        }
    }
}

@Preview
@Composable
private fun DelivaryUserLoadingDialogPreview() = DelivaryUserTheme {
    DelivaryUserLoadingDialog()
}