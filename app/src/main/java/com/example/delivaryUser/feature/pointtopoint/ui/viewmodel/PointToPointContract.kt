package com.example.delivaryUser.feature.pointtopoint.ui.viewmodel

import com.example.delivaryUser.common.ui.filedstate.TextFieldState
import com.example.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.example.delivaryUser.service.address.domain.models.domain.Address

sealed interface PointToPointContract {
    sealed interface Action {
        data object ChooseSenderAddress : Action
        data object ChooseReceiverAddress : Action
        data object AddSenderAddress : Action
        data object AddReceiverAddress : Action
        data object OnCheckOutClicked : Action
        data object ExpandDropDownMenu : Action
        data class ChooseOrderType(val id: Int) : Action
        data class OnEstimatedPriceChange(val price: String) : Action
        data class OnCommentChange(val comment: String) : Action
        data object DismissDropDownMenu : Action
        data object OnBackClicked : Action
    }

    data class State(
        val orderTypes: List<OrderPurposeUiState> = emptyList(),
        val senderAddress: AddressUiState = AddressUiState(),
        val receiverAddress: AddressUiState = AddressUiState(),
        val selectedOrderType: TextFieldState = TextFieldState(),
        val estimatedPrice: TextFieldState = TextFieldState(),
        val comment: TextFieldState = TextFieldState(),
        val deliveryFees: Double = 0.0,
        val isExpanded: Boolean = false,
    )

    data class AddressUiState(
        val address: Address = Address(
            id = 0,
            phone1 = "",
            phone2 = "",
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
    ) {
        val addressName = listOfNotNull(
            address.buildingNumber.takeIf { it.isNotBlank() },
            address.street.takeIf { it.isNotBlank() },
            address.region.takeIf { it.isNotBlank() },
            address.area.takeIf { it.isNotBlank() }
        ).joinToString(", ")
    }

    data class OrderPurposeUiState(
        val orderPurpose: OrderPurpose = OrderPurpose(
            id = 0,
            name = "",
            status = ""
        ),
        val isSelected: Boolean = false,
    )
}