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
import com.example.delivaryUser.common.ui.extension.asString
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressContract
import com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddUpdateAddressScreen(viewModel: SaveAddressViewModel= koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddUpdateAddressScreenContent(state = state, onActionTrigger = viewModel::onActionTrigger)
}

@Composable
fun AddUpdateAddressScreenContent(
    state: SaveAddressContract.State,
    onActionTrigger: (SaveAddressContract.Action) -> Unit
) {
    val scrollState = rememberScrollState()
    DelivaryUserScreen(
        scrollState = scrollState, isImePaddingEnabled = true,
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = {onActionTrigger(SaveAddressContract.Action.OnBackClick) },
                content = { Text(stringResource(R.string.add_new_address)) },
            )
        },
        contentPadding = PaddingValues(16.dp),
        contentHorizontalAlignment = Alignment.Start
    ) {

        Text(
            text = stringResource(R.string.governorate),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.region.value,
            enabled = false,
            onValueChange = {},
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.area),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.area.value,
            enabled = false,
            onValueChange = {},
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.phone1),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone1.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnPhone1Changed(it))},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            supportingText = state.phone1.error.asString()
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.phone2),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.phone2.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnPhone2Changed(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            supportingText = state.phone2.error.asString()
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.street),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.street.value,
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnStreetChanged(it)) },
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.building_number),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.buildingNumber.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnBuildingNumberChanged(it)) },
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.floor_number),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary,
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.floorNumber.value,
            onValueChange = { onActionTrigger(SaveAddressContract.Action.OnFloorNumberChanged(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.apartment_number),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value =state.apartmentNumber.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnApartmentNumberChanged(it)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.special_sign),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.specialSign.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnSpecialSignChanged(it)) },
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.extra_info),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.secondary
        )
        DelivaryUserTextInputField(
            modifier = Modifier.fillMaxWidth(),
            value = state.extraInfo.value,
            onValueChange = {onActionTrigger(SaveAddressContract.Action.OnExtraInfoChanged(it)) },
        )

        DelivaryUserButtonPrimary(
            label = stringResource(R.string.save),
            modifier = Modifier.fillMaxWidth(),
            onClick = { onActionTrigger(SaveAddressContract.Action.OnSaveAddressClicked)})

    }
}

@Composable
@PreviewAllVariants
private fun AddUpdateAddressScreenPreview() = DelivaryUserTheme {
    AddUpdateAddressScreenContent(state = SaveAddressContract.State(), onActionTrigger = {})
}