package com.hyperdesign.delivaryUser.feature.authentication.login.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonSecondary
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserPasswordTextField
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.login.ui.viewmodel.LoginContract
import com.hyperdesign.delivaryUser.feature.authentication.login.ui.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun LoginContent(state: LoginContract.State, action: (LoginContract.Action) -> Unit) {
    DelivaryUserScreen(
        isImePaddingEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier.padding(top = 32.dp),
            painter = painterResource(R.drawable.img_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(0.129f))
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone.value,
            placeholder = stringResource(R.string.phone_number),
            onValueChange = { action(LoginContract.Action.PhoneChanged(phoneNumber = it)) },
            supportingText = state.phone.error.asString()
        )
        DelivaryUserPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password.value,
            onValueChange = { action(LoginContract.Action.PasswordChanged(password = it)) },
            supportingText = state.password.error.asString()
        )
        RememberPassword(
            checked = state.rememberMe,
            onCheckChanged = {
                action(LoginContract.Action.RememberMeClicked)
            },
            onForgetPasswordClicked = {
                action(LoginContract.Action.ForgotPasswordClicked)
            }
        )
        Spacer(modifier = Modifier.weight(0.1f))
        DelivaryUserButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.login),
            isEnabled = state.phone.error == null && state.password.error == null,
            onClick = { action(LoginContract.Action.LoginClicked) })

        Spacer(modifier = Modifier.size(8.dp))
        DelivaryUserButtonSecondary(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.login_with_google),
            onClick = { action(LoginContract.Action.GoogleSignInClicked) }
        )

        RegisterNewAccount(
            modifier = Modifier.padding(top = 8.dp),
            registerClicked = { action(LoginContract.Action.RegisterClicked) }
        )
        Spacer(modifier = Modifier.weight(0.173f))
    }
}

@Composable
private fun RememberPassword(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckChanged: () -> Unit,
    onForgetPasswordClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Checkbox(
            modifier = Modifier.size(16.dp),
            checked = checked,
            onCheckedChange = {
                onCheckChanged()
            },
            colors = CheckboxDefaults.colors(

            ).copy(
                uncheckedBoxColor = DelivaryUserTheme.colors.background.surfaceHigh,
                uncheckedBorderColor = DelivaryUserTheme.colors.secondary,
                checkedBoxColor = DelivaryUserTheme.colors.primary,
            )
        )
        Text(
            text = stringResource(R.string.remember_me),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.clickableWithNoRipple {
                onForgetPasswordClicked()
            },
            text = stringResource(R.string.forgot_password),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
private fun RegisterNewAccount(modifier: Modifier = Modifier, registerClicked: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.do_not_have_and_account),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier
                .clickableWithNoRipple {
                    registerClicked()
                }
                .padding(start = 4.dp),
            text = stringResource(R.string.register),
            style = DelivaryUserTheme.typography.title.extraLarge,
            color = DelivaryUserTheme.colors.primary
        )
    }
}

@Composable
@PreviewAllVariants
private fun LoginScreenPreview() = DelivaryUserTheme {
    LoginContent(state = LoginContract.State(), action = {})
}