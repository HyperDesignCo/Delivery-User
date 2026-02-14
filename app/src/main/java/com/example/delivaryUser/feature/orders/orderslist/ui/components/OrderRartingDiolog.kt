package com.example.delivaryUser.feature.orders.orderslist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.example.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersContract

@Composable
fun OrderRatingCard(
    rate: OrdersContract.RateDialogUiState,
    onCommentChanged: (String) -> Unit,
    onSendClicked: () -> Unit,
    onRatingClicked: (Int) -> Unit,
    onDismissClicked: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissClicked() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.End)
                    .clickableWithNoRipple {
                        onDismissClicked()
                    },
                imageVector = ImageVector.vectorResource(R.drawable.ic_cancel),
                contentDescription = null,
                tint = DelivaryUserTheme.colors.secondary
            )
            Text(
                modifier = Modifier.padding(top = 25.dp),
                text = stringResource(R.string.order).plus(" ").plus("15253364"),
                color = DelivaryUserTheme.colors.secondary,
                style = DelivaryUserTheme.typography.title.large
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.from).plus(" ").plus("seoudi supermarket ")
            )
            Row(
                modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 0..4) {
                    Icon(
                        modifier = Modifier.clickableWithNoRipple {
                            onRatingClicked(i)
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = if (rate.rate > i) DelivaryUserTheme.colors.status.accentColor else DelivaryUserTheme.colors.background.hint.copy(
                            alpha = 0.2f
                        )
                    )
                }
            }
            DelivaryUserTextInputField(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth(),
                maxLines = 3,
                value = rate.comment,
                colors = DeliveryUserTextInputFieldDefaults.color(
                    focusedBorderColor = DelivaryUserTheme.colors.secondary,
                    unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                    disabledBorderColor = DelivaryUserTheme.colors.secondary,
                    contentColor = DelivaryUserTheme.colors.background.hint.copy(0.50f),
                    unfocusedContainerColor = DelivaryUserTheme.colors.background.hint.copy(0.10f),
                    focusedContainerColor = DelivaryUserTheme.colors.background.hint.copy(0.10f),
                ),
                onValueChange = { value -> onCommentChanged(value) },
                placeholder = stringResource(id = R.string.comment),
                textStyle = DelivaryUserTheme.typography.body.medium,
                shape = RoundedCornerShape(20.dp)
            )
            DelivaryUserButtonPrimary(label = stringResource(R.string.send), onClick = { onSendClicked() })
        }
    }
}

@Composable
@PreviewAllVariants
fun OrderRatingCardPreview() = DelivaryUserTheme {
    OrderRatingCard(
        rate = OrdersContract.RateDialogUiState(),
        onCommentChanged = {},
        onSendClicked = {},
        onRatingClicked = {},
        onDismissClicked = {}
    )
}