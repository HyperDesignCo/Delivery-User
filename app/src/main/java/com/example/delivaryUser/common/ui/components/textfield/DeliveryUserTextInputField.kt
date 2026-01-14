package com.example.delivaryUser.common.ui.components.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.extension.autoMirror
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.R

@Composable
fun DelivaryUserTextInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = DeliveryUserTextInputFieldDefaults.isEnabled,
    textStyle: TextStyle = DeliveryUserTextInputFieldDefaults.textStyle,
    placeholder: String? = null,
    @DrawableRes leadingIconRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions,
    keyboardActions: KeyboardActions = DeliveryUserTextInputFieldDefaults.keyboardActions,
    singleLine: Boolean = DeliveryUserTextInputFieldDefaults.singleLine,
    maxLines: Int = DeliveryUserTextInputFieldDefaults.maxLines,
    minLines: Int = DeliveryUserTextInputFieldDefaults.minLines,
    shape: Shape = DeliveryUserTextInputFieldDefaults.shape,
    colors: DeliveryUserTextInputFieldColors = DeliveryUserTextInputFieldDefaults.color(),
    textAlign: TextAlign = DeliveryUserTextInputFieldDefaults.textAlign,
) {

    OutlinedTextField(
        modifier = modifier,
        enabled = enabled,
        value = value,
        textStyle = DelivaryUserTheme.typography.body.medium,
        leadingIcon = leadingIconRes?.let {
            {
                DelivaryUserTextInputFieldIcon(contentColor = colors.contentColor, leadingIconRes = leadingIconRes)
            }
        },
        trailingIcon = trailingIconRes?.let {
            {
                DelivaryUserTextInputFieldIcon(contentColor = colors.contentColor, leadingIconRes = trailingIconRes)

            }
        },
        supportingText = {
            supportingText?.let {
                Text(
                    text = it,
                    color = colors.errorContainerColor,
                    style = DeliveryUserTextInputFieldDefaults.supportingTextStyle,
                    minLines = 1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = textAlign
                )
            }
        },
        onValueChange = { newValue ->
            onValueChange(newValue)
        },
        placeholder = {
            placeholder?.let {
                Text(
                    text = it,
                    style = textStyle,
                    color = colors.contentColor,
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = colors.unfocusedBorderColor,
            focusedBorderColor = colors.focusedBorderColor,
            disabledBorderColor = colors.disabledBorderColor,
            unfocusedContainerColor = colors.unfocusedContainerColor,
            focusedContainerColor = colors.focusedContainerColor,
            errorContainerColor = colors.errorContainerColor,
        ),
        shape = shape,
        minLines = minLines,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        isError = isError
    )
}

data class DeliveryUserTextInputFieldColors(
    val focusedBorderColor: Color = Color.Unspecified,
    val unfocusedBorderColor: Color = Color.Unspecified,
    val disabledBorderColor: Color = Color.Unspecified,
    val focusedContainerColor: Color = Color.Unspecified,
    val unfocusedContainerColor: Color = Color.Unspecified,
    val errorContainerColor: Color = Color.Unspecified,
    val contentColor: Color = Color.Unspecified,
)

object DeliveryUserTextInputFieldDefaults {
    val textStyle
        @Composable
        get() = DelivaryUserTheme.typography.body.medium
    val supportingTextStyle
        @Composable
        get() = DelivaryUserTheme.typography.label.small
    val shape = RoundedCornerShape(8.dp)
    val singleLine: Boolean = false
    val minLines = 1
    val maxLines = if (singleLine) 1 else Int.MAX_VALUE
    val keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next
    )
    val keyboardActions: KeyboardActions = KeyboardActions.Default
    val iconSize = 20.dp
    val isEnabled = true
    val textAlign = TextAlign.Start

    @Composable
    fun color(
        focusedBorderColor: Color = DelivaryUserTheme.colors.secondary,
        unfocusedBorderColor: Color = DelivaryUserTheme.colors.secondary,
        disabledBorderColor: Color = DelivaryUserTheme.colors.secondary,
        focusedContainerColor: Color = DelivaryUserTheme.colors.background.surfaceHigh,
        unfocusedContainerColor: Color = DelivaryUserTheme.colors.background.surfaceHigh,
        errorContainerColor: Color = DelivaryUserTheme.colors.status.redAccent,
        contentColor: Color = DelivaryUserTheme.colors.secondary,
    ) = DeliveryUserTextInputFieldColors(
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        disabledBorderColor = disabledBorderColor,
        focusedContainerColor = focusedContainerColor,
        unfocusedContainerColor = unfocusedContainerColor,
        errorContainerColor = errorContainerColor,
        contentColor = contentColor
    )
}

@Composable
private fun DelivaryUserTextInputFieldIcon(@DrawableRes leadingIconRes: Int, contentColor: Color) {
    Icon(
        modifier = Modifier
            .size(DeliveryUserTextInputFieldDefaults.iconSize)
            .autoMirror(),
        tint = contentColor,
        imageVector = ImageVector.vectorResource(leadingIconRes),
        contentDescription = null
    )
}

@PreviewAllVariants
@Composable
@Preview(showBackground = true)
fun DelivaryUserTextInputFieldPreview() = DelivaryUserTheme {
    DelivaryUserTextInputField(
        value = "password",
        onValueChange = {},
        placeholder = "password",
        colors = DeliveryUserTextInputFieldDefaults.color(
            unfocusedBorderColor = DelivaryUserTheme.colors.secondary,
            focusedBorderColor = DelivaryUserTheme.colors.secondary,
            disabledBorderColor = DelivaryUserTheme.colors.secondary,
            unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            errorContainerColor = DelivaryUserTheme.colors.status.redAccent,
        ),
        leadingIconRes = R.drawable.ic_profile,
        trailingIconRes = R.drawable.ic_password,
        supportingText = "password does not match"
    )
}