package com.example.delivaryUser.feature.addresslist.ui.viewmodel

import com.example.delivaryUser.service.address.domain.models.domain.Address

sealed interface AddressListContract {
    sealed interface Action {
        data class SelectAddress(val address: AddressUiState) : Action
        data object SaveAddress : Action
        data object OnBackClicked : Action
    }

    data class State(
        val addressList: List<AddressUiState> = emptyList(),
        val selectedAddress: AddressUiState = AddressUiState(
            address = Address(
                id = 0,
                firstPhone = "",
                secondPhone = "",
                street = "",
                buildingNumber = "",
                floorNumber = "",
                apartmentNumber = "",
                specialSign = "",
                latitude = "",
                longitude = "",
                user = "",
                area = "",
                areaId = "0",
                region = "",
                regionId = "0",
                countryId = ""
            ),
        ),
    )

    data class AddressUiState(
        val address: Address = Address(
            id = 0,
            firstPhone = "",
            secondPhone = "",
            street = "",
            buildingNumber = "",
            floorNumber = "",
            apartmentNumber = "",
            specialSign = "",
            latitude = "",
            longitude = "",
            user = "",
            area = "",
            areaId = "0",
            region = "",
            regionId = "0",
            countryId = ""
        ),
        val isSelected: Boolean = false,
    ) {
        val addressName = "${address.buildingNumber}, ${address.street}, ${address.region}, ${address.area}"
    }
}