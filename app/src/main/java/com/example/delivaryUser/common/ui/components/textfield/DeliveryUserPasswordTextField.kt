package com.example.delivaryUser.common.ui.components.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.R

@Composable
fun DelivaryUserPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    supportingText: String? = null,
) {
    DelivaryUserTextInputField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = stringResource(R.string.password),
        colors = DelivaryUserPasswordTextFieldDefaults.colors,
        trailingIconRes = R.drawable.ic_password,
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
            ),
        visualTransformation = PasswordVisualTransformation()
    )
}

object DelivaryUserPasswordTextFieldDefaults {
    val colors
        @Composable get() = DeliveryUserTextInputFieldDefaults.color(
            unfocusedBorderColor = DelivaryUserTheme.colors.secondary,
            focusedBorderColor = DelivaryUserTheme.colors.secondary,
            disabledBorderColor = DelivaryUserTheme.colors.secondary,
            unfocusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            focusedContainerColor = DelivaryUserTheme.colors.background.surfaceHigh,
            errorContainerColor = DelivaryUserTheme.colors.status.redAccent,
        )
}

@Composable
@PreviewAllVariants
private fun DelivaryUserPasswordTextFieldPreview() = DelivaryUserTheme {
    DelivaryUserPasswordTextField(value = "", onValueChange = {})
}