package com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.ui.viewmodel.VerifyOtpContract
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.ui.viewmodel.VerifyOtpViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun VerifyOtpScreen(viewModel: VerifyOtpViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    VerifyOtpContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun VerifyOtpContent(state: VerifyOtpContract.State, action: (VerifyOtpContract.Action) -> Unit) {
    DelivaryUserScreen(
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 28.dp),
            text = stringResource(R.string.verify_otp),
            style = DelivaryUserTheme.typography.headline.extraLarge,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            modifier = Modifier.padding(top = 22.dp),
            text = stringResource(R.string.please_enter_the_otp_sent_to_your_phone_number),
            style = DelivaryUserTheme.typography.body.large,
            color = DelivaryUserTheme.colors.secondary,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.098f))
        OtpCodes(state = state, action = action)
        Spacer(modifier = Modifier.weight(0.4229f))
        ResendCode(
            modifier = Modifier.padding(bottom = 38.dp),
            onVerifyClicked = { action(VerifyOtpContract.Action.VerifyClicked) },
            timer = state.timer,
            onResendClicked = { action(VerifyOtpContract.Action.ResendClicked) },
            isEnabled = state.isResendEnabled
        )
    }
}

@Composable
private fun OtpCodes(state: VerifyOtpContract.State, action: (VerifyOtpContract.Action) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DelivaryUserTextInputField(
            modifier = Modifier.weight(1f),
            value = state.firstDigit.value,
            onValueChange = { action(VerifyOtpContract.Action.FirstDigitChanged(it)) },
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            textAlign = TextAlign.Center,
            textStyle = DelivaryUserTheme.typography.headline.large
        )
        DelivaryUserTextInputField(
            modifier = Modifier.weight(1f),
            value = state.secondDigit.value,
            onValueChange = { action(VerifyOtpContract.Action.SecondDigitChanged(it)) },
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            textAlign = TextAlign.Center,
            textStyle = DelivaryUserTheme.typography.headline.large
        )
        DelivaryUserTextInputField(
            modifier = Modifier.weight(1f),
            value = state.thirdDigit.value,
            onValueChange = { action(VerifyOtpContract.Action.ThirdDigitChanged(it)) },
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            textAlign = TextAlign.Center,
            textStyle = DelivaryUserTheme.typography.headline.large
        )
        DelivaryUserTextInputField(
            modifier = Modifier.weight(1f),
            value = state.fourthDigit.value,
            onValueChange = { action(VerifyOtpContract.Action.FourthDigitChanged(it)) },
            keyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions.copy(
                keyboardType = KeyboardType.Number
            ),
            textAlign = TextAlign.Center,
            textStyle = DelivaryUserTheme.typography.headline.large
        )
    }
}

@Composable
private fun ResendCode(
    onVerifyClicked: () -> Unit,
    onResendClicked: () -> Unit,
    timer: String,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.clickableWithNoRipple(
                enabled = isEnabled,
                onClick = { onResendClicked() }
            ),
            text = stringResource(R.string.resend_code).plus(" ").plus(timer),
            style = DelivaryUserTheme.typography.body.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserButtonPrimary(
            modifier = Modifier.width(160.dp),
            label = stringResource(R.string.verify),
            isEnabled = true,
            onClick = { onVerifyClicked() })
    }
}

@Composable
@PreviewAllVariants
private fun VerifyOtpCodePreview() = DelivaryUserTheme {
    VerifyOtpContent(state = VerifyOtpContract.State(), action = {})
}