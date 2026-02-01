package com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel

import com.example.delivaryUser.common.ui.filedstate.TextFieldState

sealed interface SaveAddressContract {

    sealed interface Action {
        data class OnFirstPhoneChanged(val phone: String) : Action
        data class OnSecondPhoneChanged(val phone: String) : Action
        data class OnStreetChanged(val street: String) : Action
        data class OnBuildingNumberChanged(val buildingNumber: String) : Action
        data class OnFloorNumberChanged(val floorNumber: String) : Action
        data class OnApartmentNumberChanged(val apartmentNumber: String) : Action
        data class OnSpecialSignChanged(val specialSign: String) : Action
        data class OnExtraInfoChanged(val extraInfo: String) : Action
        data object OnSaveAddressClicked : Action
        data object OnBackClick: Action
    }

    data class State(
        val region: TextFieldState = TextFieldState(),
        val area: TextFieldState = TextFieldState(),
        val firstPhone: TextFieldState = TextFieldState(),
        val secondPhone: TextFieldState = TextFieldState(),
        val street: TextFieldState = TextFieldState(),
        val buildingNumber: TextFieldState = TextFieldState(),
        val floorNumber: TextFieldState = TextFieldState(),
        val apartmentNumber: TextFieldState = TextFieldState(),
        val specialSign: TextFieldState = TextFieldState(),
        val extraInfo: TextFieldState = TextFieldState(),
        val regionId: String = "",
        val areaId: String = "",
        val latitude: String = "",
        val longitude: String = "",
        val name:String = "",
    )
}