package com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserPasswordTextField
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.viewmodel.ChangePasswordContract
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.ui.viewmodel.ChangePasswordViewModel
import org.koin.compose.koinInject

@Composable
fun ChangePasswordScreen(viewModel: ChangePasswordViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ChangePasswordContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun ChangePasswordContent(
    state: ChangePasswordContract.State,
    action: (ChangePasswordContract.Action) -> Unit,
) {
    DelivaryUserScreen(
        isImePaddingEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.create_new_password),
            style = DelivaryUserTheme.typography.headline.extraLarge,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier.padding(top = 22.dp),
            text = stringResource(R.string.new_password_must_be_different),
            style = DelivaryUserTheme.typography.body.extraLarge,
            color = DelivaryUserTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.098f))
        DelivaryUserPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.newPassword.value,
            placeholder = stringResource(R.string.new_password),
            onValueChange = { action(ChangePasswordContract.Action.OnNewPasswordChanged(password = it)) },
            supportingText = state.newPassword.error.asString(),
        )
        DelivaryUserPasswordTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.confirmationPassword.value,
            placeholder = stringResource(R.string.confirm_password),
            onValueChange = { action(ChangePasswordContract.Action.OnConfirmationPasswordChanged(password = it)) },
            supportingText = state.confirmationPassword.error.asString(),
        )
        Spacer(modifier = Modifier.weight(0.4229f))
        DelivaryUserButtonPrimary(
            modifier = Modifier
                .width(160.dp)
                .align(Alignment.End),
            label = stringResource(R.string.send),
            isEnabled = state.newPassword.isError().not() && state.confirmationPassword.isError().not(),
            onClick = { action(ChangePasswordContract.Action.OnSendClicked) })
        Spacer(modifier = Modifier.weight(0.0518f))
    }
}

@Composable
@PreviewAllVariants
private fun ChangePasswordContentPreview() = DelivaryUserTheme {
    ChangePasswordContent(state = ChangePasswordContract.State(), action = {})
}