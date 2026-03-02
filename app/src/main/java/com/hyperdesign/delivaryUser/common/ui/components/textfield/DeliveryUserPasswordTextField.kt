package com.hyperdesign.delivaryUser.common.ui.components.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.password),
    supportingText: String? = null,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    DelivaryUserTextInputField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = placeholder,
        colors = DelivaryUserPasswordTextFieldDefaults.colors,
        onTrailingIconClicked = { isPasswordVisible = isPasswordVisible.not() },
        trailingIconRes = DelivaryUserPasswordTextFieldDefaults.passwordVisibilityIcon(isVisible = isPasswordVisible),
        supportingText = supportingText,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        visualTransformation = DelivaryUserPasswordTextFieldDefaults.passwordVisualTransformation(isPasswordVisible)
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
            cursorColor = DelivaryUserTheme.colors.primary
        )

    @Composable
    fun passwordVisibilityIcon(isVisible: Boolean): Int = remember(isVisible) {
        if (isVisible) R.drawable.ic_password_visible else R.drawable.ic_password_invisible
    }

    @Composable
    fun passwordVisualTransformation(isVisible: Boolean): VisualTransformation =
        remember(isVisible) { if (isVisible) VisualTransformation.None else PasswordVisualTransformation() }
}

@Composable
@PreviewAllVariants
private fun DelivaryUserPasswordTextFieldPreview() = DelivaryUserTheme {
    DelivaryUserPasswordTextField(value = "", onValueChange = {})
}