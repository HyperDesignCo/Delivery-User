package com.hyperdesign.delivaryUser.feature.chatwithdelivery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun ChatInputField(
    messageValue: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DelivaryUserTextInputField(
            value = messageValue,
            onValueChange = onMessageChange,
            modifier = Modifier.weight(1f),
            placeholder = stringResource(R.string.type_a_message),
            singleLine = true,
            maxLines = 1,
            enabled = true,
            readOnly = false,
            shape = RoundedCornerShape(30.dp),
        )

        IconButton(
            onClick = onSendClick,
            enabled = messageValue.isNotBlank(),
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = if (messageValue.isNotBlank()) {
                        DelivaryUserTheme.colors.status.greenAccent
                    } else {
                        DelivaryUserTheme.colors.background.surfaceHigh
                    },
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                tint = if (messageValue.isNotBlank()) {
                    DelivaryUserTheme.colors.background.surfaceHigh
                } else {
                    DelivaryUserTheme.colors.status.grayAccent
                },
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
@PreviewAllVariants
private fun ChatInputFieldPreview() = DelivaryUserTheme {
    ChatInputField(messageValue = "Hello", onMessageChange = {}, onSendClick = {})
}