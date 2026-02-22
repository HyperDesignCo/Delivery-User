package com.hyperdesign.delivaryUser.feature.authentication.register.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScaffold
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserPasswordTextField
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.register.ui.viewmodel.RegisterContract
import com.hyperdesign.delivaryUser.feature.authentication.register.ui.viewmodel.RegisterViewModel
import org.koin.compose.koinInject

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RegisterContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun RegisterContent(state: RegisterContract.State, action: (RegisterContract.Action) -> Unit) {
    Column(
        modifier = Modifier

            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
            .padding(horizontal = 16.dp).padding(DelivaryUserScaffold.innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(top = 32.dp),
            painter = painterResource(R.drawable.img_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.weight(0.129f))
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.name.value,
            placeholder = stringResource(R.string.name),
            onValueChange = { action(RegisterContract.Action.NameChanged(name = it)) },
            supportingText = state.name.error.asString()
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.email.value,
            placeholder = stringResource(R.string.email),
            onValueChange = { action(RegisterContract.Action.EmailChanged(email = it)) },
            supportingText = state.email.error.asString()
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone.value,
            placeholder = stringResource(R.string.phone_number),
            onValueChange = { action(RegisterContract.Action.PhoneChanged(phoneNumber = it)) },
            supportingText = state.phone.error.asString()
        )
        DelivaryUserPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password.value,
            onValueChange = { action(RegisterContract.Action.PasswordChanged(password = it)) },
            supportingText = state.password.error.asString()
        )
        Spacer(modifier = Modifier.weight(0.225f))
        DelivaryUserButtonPrimary(
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.register),
            isEnabled = state.phone.error == null && state.password.error == null && state.email.error == null && state.name.error == null,
            onClick = { action(RegisterContract.Action.RegisterClicked) })
        LoginAccount(loginClicked = { action(RegisterContract.Action.LoginClicked) })
        Spacer(modifier = Modifier.weight(0.173f))
    }
}

@Composable
private fun LoginAccount(
    loginClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.have_account),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier
                .clickableWithNoRipple {
                    loginClicked()
                }
                .padding(start = 4.dp),
            text = stringResource(R.string.login),
            style = DelivaryUserTheme.typography.title.extraLarge,
            color = DelivaryUserTheme.colors.primary)
    }
}

@Composable
@PreviewAllVariants
private fun RegisterScreenPreview() = DelivaryUserTheme {
    RegisterContent(
        state = RegisterContract.State(), action = {})
}