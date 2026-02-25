package com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.view

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
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.hyperdesign.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.hyperdesign.delivaryUser.common.ui.extension.asString
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.viewmodel.VerifyPhoneContract
import com.hyperdesign.delivaryUser.feature.authentication.verifyphone.ui.viewmodel.VerifyPhoneViewModel
import org.koin.compose.koinInject

@Composable
fun VerifyPhoneScreen(viewModel: VerifyPhoneViewModel = koinInject()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    VerifyPhoneContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun VerifyPhoneContent(state: VerifyPhoneContract.State, action: (VerifyPhoneContract.Action) -> Unit) {
    DelivaryUserScreen(
        modifier = Modifier.navigationBarsPadding(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.verify_phone),
            style = DelivaryUserTheme.typography.headline.extraLarge,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier.padding(top = 22.dp),
            text = stringResource(R.string.last_step_verify_phone),
            style = DelivaryUserTheme.typography.body.extraLarge,
            color = DelivaryUserTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.098f))
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone.value,
            placeholder = stringResource(R.string.phone_number),
            onValueChange = { action(VerifyPhoneContract.Action.PhoneChanged(phone = it)) },
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
            onClick = { action(VerifyPhoneContract.Action.SendOtpClicked) })
        Spacer(modifier = Modifier.weight(0.0518f))
    }
}

@Composable
@PreviewAllVariants
private fun VerifyPhoneScreenPreview() = DelivaryUserTheme {
    VerifyPhoneContent(state = VerifyPhoneContract.State(), action = {})
}