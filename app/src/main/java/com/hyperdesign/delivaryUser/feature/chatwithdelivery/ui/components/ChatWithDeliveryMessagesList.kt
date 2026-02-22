package com.hyperdesign.delivaryUser.feature.chatwithdelivery.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryContract


@Composable
fun ChatMessageBubble(
    message: ChatWithDeliveryContract.ChatWithDeliveryUiState,
    modifier: Modifier = Modifier,
) {
    val isCurrentUser = message.senderType == "user"

    AnimatedVisibility(
        visible = true, enter = expandVertically() + fadeIn(), modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = if (isCurrentUser) {
                Arrangement.End
            } else {
                Arrangement.Start
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.85f)
                    .background(
                        color = if (isCurrentUser) {
                            DelivaryUserTheme.colors.primary
                        } else {
                            DelivaryUserTheme.colors.status.grayAccent
                        }, shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp,
                            bottomStart = if (isCurrentUser) 20.dp else 0.dp,
                            bottomEnd = if (isCurrentUser) 0.dp else 20.dp
                        )
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column {
                    Text(
                        text = message.message,
                        color = if (isCurrentUser)
                            DelivaryUserTheme.colors.background.surfaceHigh
                        else DelivaryUserTheme.colors.secondary,
                        style = DelivaryUserTheme.typography.body.large,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = message.createAt,
                        color = if (isCurrentUser) {
                            DelivaryUserTheme.colors.secondary
                        } else {
                            DelivaryUserTheme.colors.secondary
                        },
                        style = DelivaryUserTheme.typography.label.large,
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
@PreviewAllVariants
private fun ChatMessageBubblePreview() = DelivaryUserTheme {
    ChatMessageBubble(message = ChatWithDeliveryContract.ChatWithDeliveryUiState())
}