package com.example.delivaryUser.feature.addresslist.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.addresslist.ui.viewmodel.AddressListContract
import com.example.delivaryUser.feature.addresslist.ui.viewmodel.AddressListViewModel
import com.example.delivaryUser.service.address.domain.models.domain.Address
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddressListScreen(viewModel: AddressListViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AddressContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun AddressContent(state: AddressListContract.State, action: (AddressListContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(onStartIconClicked = { action(AddressListContract.Action.OnBackClicked) }, content = {
                Text(
                    text = stringResource(id = R.string.addresses),
                    style = DelivaryUserTheme.typography.headline.large,
                    color = DelivaryUserTheme.colors.background.surfaceHigh
                )
            })
        },
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        LazyColumn(modifier = Modifier) {
            items(state.addressList.size, key = { state.addressList[it].address.id }) { index ->
                AddressItem(state.addressList[index], onAddressClicked = {
                    action(AddressListContract.Action.SelectAddress(it))
                })
                if (index != state.addressList.lastIndex)
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
                    )
            }
        }
        Spacer(Modifier.weight(1f))
        DelivaryUserButtonPrimary(
            modifier = Modifier.padding(bottom = 21.dp),
            label = stringResource(R.string.save),
            onClick = { action(AddressListContract.Action.SaveAddress) })
    }
}

@Composable
private fun AddressItem(
    address: AddressListContract.AddressUiState,
    onAddressClicked: (AddressListContract.AddressUiState) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickableWithNoRipple {
                onAddressClicked(address)
            }
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = address.addressName,
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary,
            overflow = TextOverflow.Ellipsis
        )
        if (address.isSelected)
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_checked),
                tint = DelivaryUserTheme.colors.primary,
                contentDescription = null
            )
    }
}

@Composable
@Preview
private fun AddressListScreenPreview() = DelivaryUserTheme {
    AddressContent(
        state = AddressListContract.State(
            addressList = listOf(
                AddressListContract.AddressUiState(
                    address = Address(
                        id = 1,
                        phone1 = "+201234567890",
                        phone2 = "+201098765432",
                        street = "El Tahrir Street",
                        buildingNumber = "15",
                        floorNumber = "3",
                        apartmentNumber = "12",
                        specialSign = "Near the pharmacy",
                        latitude = "30.0444",
                        longitude = "31.2357",
                        user = "1",
                        area = "Dokki",
                        areaId = "1",
                        region = "Giza",
                        regionId = "1",
                        countryId = "1"
                    ),
                    isSelected = true
                ),
                AddressListContract.AddressUiState(
                    address =
                        Address(
                            id = 2,
                            phone1 = "+201111222333",
                            phone2 = "",
                            street = "Ahmed Orabi Street",
                            buildingNumber = "42",
                            floorNumber = "5",
                            apartmentNumber = "8",
                            specialSign = "Next to the school",
                            latitude = "30.0626",
                            longitude = "31.2497",
                            user = "1",
                            area = "Mohandessin",
                            areaId = "2",
                            region = "Giza",
                            regionId = "1",
                            countryId = "1"
                        )
                ),
                AddressListContract.AddressUiState(
                    address =
                        Address(
                            id = 3,
                            phone1 = "+201555666777",
                            phone2 = "+201444333222",
                            street = "26th of July Street",
                            buildingNumber = "88",
                            floorNumber = "2",
                            apartmentNumber = "4",
                            specialSign = "Opposite the bank",
                            latitude = "30.0561",
                            longitude = "31.2394",
                            user = "1",
                            area = "Zamalek",
                            areaId = "3",
                            region = "Cairo",
                            regionId = "2",
                            countryId = "1"
                        )
                )
            ),
            selectedAddress =
                AddressListContract.AddressUiState(
                    address = Address(
                        id = 2,
                        phone1 = "+201111222333",
                        phone2 = "",
                        street = "Ahmed Orabi Street",
                        buildingNumber = "42",
                        floorNumber = "5",
                        apartmentNumber = "8",
                        specialSign = "Next to the school",
                        latitude = "30.0626",
                        longitude = "31.2497",
                        user = "1",
                        area = "Mohandessin",
                        areaId = "2",
                        region = "Giza",
                        regionId = "1",
                        countryId = "1"
                    )
                )
        ),
        action = {}
    )
}