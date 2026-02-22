package com.example.delivaryUser.feature.authentication.forgetpassword.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.example.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.example.delivaryUser.common.ui.extension.asString
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.authentication.forgetpassword.ui.viewmodel.ForgetPasswordContract
import com.example.delivaryUser.feature.authentication.forgetpassword.ui.viewmodel.ForgetPasswordViewModel
import org.koin.compose.koinInject

@Composable
fun ForgetPasswordScreen(viewModel: ForgetPasswordViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ForgetPasswordContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun ForgetPasswordContent(
    state: ForgetPasswordContract.State,
    action: (ForgetPasswordContract.Action) -> Unit,
) {
    DelivaryUserScreen(
        modifier = Modifier.navigationBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.forget_password),
            style = DelivaryUserTheme.typography.headline.extraLarge,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier.padding(top = 22.dp),
            text = stringResource(R.string.please_enter_phone_number_to_send_code),
            style = DelivaryUserTheme.typography.body.extraLarge,
            color = DelivaryUserTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.098f))
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone.value,
            placeholder = stringResource(R.string.phone_number),
            onValueChange = { action(ForgetPasswordContract.Action.PhoneChanged(phone = it)) },
            supportingText = state.phone.error.asString(),
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.weight(0.4229f))
        DelivaryUserButtonPrimary(
            modifier = Modifier
                .width(160.dp)
                .align(Alignment.End),
            label = stringResource(R.string.send_otp),
            isEnabled = true,
            onClick = { action(ForgetPasswordContract.Action.SendOtpClicked) })
        Spacer(modifier = Modifier.weight(0.0518f))
    }
}

@Composable
@PreviewAllVariants
private fun ForgetPasswordScreenPreview() = DelivaryUserTheme {
    ForgetPasswordContent(
        state = ForgetPasswordContract.State(),
        action = {}
    )
}