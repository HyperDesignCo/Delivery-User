package com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.viewmodel.PointToPointContract

@Composable
fun PointToPointForm(
    estimatedPrice: TextFieldState,
    comment: TextFieldState,
    deliveryFees: Double,
    orderTypes: List<PointToPointContract.OrderPurposeUiState>,
    selectedOrderType: TextFieldState,
    onOrderTypeChange: (Int) -> Unit,
    onEstimatedPriceChange: (String) -> Unit,
    onCommentChange: (String) -> Unit,
    expanded: Boolean,
    onDismissMenuClicked: () -> Unit,
    onExpandClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OrderTypeDropDown(
            orderTypes = orderTypes,
            selectedOrderType = selectedOrderType,
            onOrderTypeChange = onOrderTypeChange,
            onExpandClicked = onExpandClicked,
            expanded = expanded,
            onDismissMenuClicked = onDismissMenuClicked
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = estimatedPrice.value,
            onValueChange = { onEstimatedPriceChange(it) },
            textStyle = DelivaryUserTheme.typography.body.medium,
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = DeliveryUserTextInputFieldDefaults.color(
                focusedBorderColor = DelivaryUserTheme.colors.secondary,
                unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                disabledBorderColor = DelivaryUserTheme.colors.secondary,
                contentColor = DelivaryUserTheme.colors.secondary,
                unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            ),
            placeholder = stringResource(id = R.string.estimated_price),
        )
        DelivaryUserTextInputField(
            modifier = Modifier
                .height(88.dp)
                .fillMaxWidth(),
            maxLines = 3,
            value = comment.value,
            colors = DeliveryUserTextInputFieldDefaults.color(
                focusedBorderColor = DelivaryUserTheme.colors.secondary,
                unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                disabledBorderColor = DelivaryUserTheme.colors.secondary,
                contentColor = DelivaryUserTheme.colors.secondary,
                unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            ),
            onValueChange = { onCommentChange(it) },
            placeholder = stringResource(id = R.string.comment),
            textStyle = DelivaryUserTheme.typography.body.medium,
        )
        DeliveryFeesSection(deliveryFees)
    }
}

@Composable
private fun OrderTypeDropDown(
    orderTypes: List<PointToPointContract.OrderPurposeUiState>,
    selectedOrderType: TextFieldState,
    expanded: Boolean,
    onExpandClicked: () -> Unit,
    onOrderTypeChange: (Int) -> Unit,
    onDismissMenuClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        DelivaryUserTextInputField(
            modifier = Modifier
                .fillMaxWidth(),
            value = selectedOrderType.value,
            onValueChange = {},
            enabled = true,
            readOnly = true,
            placeholder = stringResource(id = R.string.order_type),
            trailingIconRes = R.drawable.ic_drop_down,
            trailingIconColor = DelivaryUserTheme.colors.primary,
            colors = DeliveryUserTextInputFieldDefaults.color(
                focusedBorderColor = DelivaryUserTheme.colors.secondary,
                unfocusedBorderColor = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f),
                disabledBorderColor = DelivaryUserTheme.colors.secondary,
                contentColor = DelivaryUserTheme.colors.secondary,
                unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
                focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            )
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickableWithNoRipple {
                    onExpandClicked()
                }
        )
        CompositionLocalProvider(
            LocalRippleConfiguration provides RippleConfiguration(color = Color.Transparent)
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { onDismissMenuClicked() },
                modifier = Modifier
                    .background(DelivaryUserTheme.colors.background.surfaceHigh)
                    .fillMaxWidth(0.9f)
            ) {
                orderTypes.forEach { type ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = type.orderPurpose.name,
                                style = DelivaryUserTheme.typography.body.medium,
                                color = DelivaryUserTheme.colors.secondary
                            )
                        },
                        onClick = {
                            onOrderTypeChange(type.orderPurpose.id)
                            onDismissMenuClicked()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DeliveryFeesSection(price: Double, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.dp, color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f))
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.delivery_fess),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = stringResource(id = R.string.egp).plus(" ").plus(price),
            style = DelivaryUserTheme.typography.title.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
@PreviewAllVariants
private fun PointToPointFormPreview() = DelivaryUserTheme {
    var expanded by remember { mutableStateOf(false) }
    PointToPointForm(
        modifier = Modifier.background(color = DelivaryUserTheme.colors.background.surfaceHigh),
        estimatedPrice = TextFieldState(),
        comment = TextFieldState(),
        onEstimatedPriceChange = {},
        onCommentChange = {},
        deliveryFees = 1.79900,
        orderTypes = emptyList(),
        selectedOrderType = TextFieldState(),
        onOrderTypeChange = {},
        onExpandClicked = { expanded = expanded.not() },
        expanded = expanded,
        onDismissMenuClicked = {
            expanded = false
        },
    )
}