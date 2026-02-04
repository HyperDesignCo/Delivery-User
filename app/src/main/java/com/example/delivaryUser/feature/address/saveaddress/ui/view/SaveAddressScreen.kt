package com.example.delivaryUser.feature.address.saveaddress.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.components.textfield.DelivaryUserTextInputField
import com.example.delivaryUser.common.ui.components.textfield.DeliveryUserTextInputFieldDefaults
import com.example.delivaryUser.common.ui.extension.asString
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressContract
import com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SaveAddressScreen(viewModel: SaveAddressViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SaveAddressContent(state = state, onActionTrigger = viewModel::onActionTrigger)
}

@Composable
fun SaveAddressContent(
    state: SaveAddressContract.State,
    onActionTrigger: (SaveAddressContract.Action) -> Unit,
) {
    DelivaryUserScreen(
         isImePaddingEnabled = true,
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { onActionTrigger(SaveAddressContract.Action.OnBackClick) },
                content = {
                    Text(
                        stringResource(R.string.add_new_address),
                        style = DelivaryUserTheme.typography.headline.large,
                        color = DelivaryUserTheme.colors.background.surfaceHigh
                    )
                },
            )
        },
        contentHorizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp),
        contentScrollState = rememberScrollState()
    ) {
        AddAddressTextFiled(label = stringResource(R.string.governorate), value = state.region.value, isEnabled = false)
        AddAddressTextFiled(label = stringResource(R.string.area), value = state.area.value, isEnabled = false)
        AddAddressTextFiled(
            label = stringResource(R.string.first_phone_number),
            value = state.firstPhone.value,
            keyOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            supportingText = state.firstPhone.error.asString(),
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnFirstPhoneChanged(it)) }
        )
        AddAddressTextFiled(
            label = stringResource(R.string.second_phone_number),
            value = state.secondPhone.value,
            keyOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            supportingText = state.secondPhone.error.asString(),
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnSecondPhoneChanged(it)) }
        )
        AddAddressTextFiled(
            label = stringResource(R.string.street),
            value = state.street.value,
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnStreetChanged(it)) },
            )
        AddAddressTextFiled(
            label = stringResource(R.string.building_number),
            value = state.buildingNumber.value,
            keyOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnBuildingNumberChanged(it)) },
        )
        AddAddressTextFiled(
            label = stringResource(R.string.floor_number),
            value = state.floorNumber.value,
            keyOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnFloorNumberChanged(it)) },
        )
        AddAddressTextFiled(
            label = stringResource(R.string.apartment_number),
            value = state.apartmentNumber.value,
            keyOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnApartmentNumberChanged(it)) },
        )
        AddAddressTextFiled(
            label = stringResource(R.string.special_sign),
            value =  state.specialSign.value,
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnSpecialSignChanged(it)) },
        )
        AddAddressTextFiled(
            label = stringResource(R.string.extra_info),
            value =  state.specialSign.value,
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnExtraInfoChanged(it)) },
        )

        DelivaryUserButtonPrimary(
            label = stringResource(R.string.save),
            isEnabled = state.firstPhone.error == null && state.secondPhone.error == null,
            modifier = Modifier.fillMaxWidth(),
            onClick = { onActionTrigger(SaveAddressContract.Action.OnSaveAddressClicked) })
    }
}

@Composable
private fun AddAddressTextFiled(
    label: String,
    value: String,
    isEnabled: Boolean = true,
    keyOptions: KeyboardOptions = DeliveryUserTextInputFieldDefaults.keyboardOptions,
    onValueChange: (String) -> Unit = {},
    supportingText: String ="",
) {
    Text(
        text = label,
        style = DelivaryUserTheme.typography.title.extraLarge,
        color = DelivaryUserTheme.colors.secondary
    )
    DelivaryUserTextInputField(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        value = value,
        enabled = isEnabled,
        keyboardOptions = keyOptions,
        onValueChange = { onValueChange(it) },
        supportingText = supportingText
    )
}

@Composable
@PreviewAllVariants
private fun SaveAddressPreview() = DelivaryUserTheme {
    SaveAddressContent(state = SaveAddressContract.State(), onActionTrigger = {})
}