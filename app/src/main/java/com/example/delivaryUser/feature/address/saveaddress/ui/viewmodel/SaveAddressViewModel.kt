package com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetSavedLocationUseCase
import com.example.delivaryUser.feature.address.saveaddress.domain.interactors.SaveAddressUseCase
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.example.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.example.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.example.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.example.delivaryUser.service.address.domain.models.domain.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveAddressViewModel(
    private val saveAddress: SaveAddressUseCase,
    private val getLocationRemote: GetLocationResponseUseCase,
    private val getLocationLocal: GetSavedLocationUseCase,
    private val saveSenderAddress: SaveSenderAddressUseCase,
    savedStateHandle: SavedStateHandle,
    private val saveRecipientAddress: SaveRecipientAddressUseCase
) : BaseViewModel<SaveAddressContract.State, SaveAddressContract.Action>(
    SaveAddressContract.State()
) {

    private val route = savedStateHandle.toRoute<IOrderGraph.SaveAddress>()

    init {
        getLocationResponse()
        getSavedLocation()
    }


    override fun onActionTrigger(action: SaveAddressContract.Action) {
        when (action) {
            is SaveAddressContract.Action.OnApartmentNumberChanged -> { onApartmentNumberChanged(action.apartmentNumber) }
            is SaveAddressContract.Action.OnBuildingNumberChanged -> { onBuildingNumberChanged(action.buildingNumber) }
            is SaveAddressContract.Action.OnExtraInfoChanged -> { onExtraInfoChanged(action.extraInfo) }
            is SaveAddressContract.Action.OnFloorNumberChanged -> { onFloorNumberChanged(action.floorNumber) }
            is SaveAddressContract.Action.OnFirstPhoneChanged -> { onFirstPhoneChanged(action.phone) }
            is SaveAddressContract.Action.OnSecondPhoneChanged -> { onSecondPhoneChanged(action.phone) }
            is SaveAddressContract.Action.OnSpecialSignChanged -> { onSpecialSignChanged(action.specialSign) }
            is SaveAddressContract.Action.OnStreetChanged -> { onStreetChanged(action.street) }
            is SaveAddressContract.Action.OnSaveAddressClicked -> { onSaveAddressClicked() }
            is SaveAddressContract.Action.OnBackClick -> { onBackClick() }
        }
    }
    private fun onApartmentNumberChanged(value: String) {
        updateState {
            copy(
                apartmentNumber = apartmentNumber.copy(value = value)
            )
        }
    }

    private fun onBuildingNumberChanged(value: String) {
        updateState {
            copy(
                buildingNumber = buildingNumber.copy(value = value)
            )
        }
    }

    private fun onExtraInfoChanged(value: String) {
        updateState {
            copy(
                extraInfo = extraInfo.copy(value = value)
            )
        }
    }

    private fun onFloorNumberChanged(value: String) {
        updateState {
            copy(
                floorNumber = floorNumber.copy(value = value)
            )
        }
    }

    private fun onFirstPhoneChanged(value: String) {
        updateState {
            copy(firstPhone = firstPhone.copy(value = value, error = null))
        }
    }

    private fun onSecondPhoneChanged(value: String) {
        updateState {
            copy(secondPhone = secondPhone.copy(value = value, error = null))
        }
    }

    private fun onSpecialSignChanged(value: String) {
        updateState {
            copy(
                specialSign = specialSign.copy(value = value)
            )
        }
    }

    private fun onStreetChanged(value: String) {
        updateState {
            copy(
                street = street.copy(value = value)
            )
        }
    }
    private fun onBackClick() {
        viewModelScope.launch(Dispatchers.IO) {
            fireNavigateUp()
        }

    }

    private fun getLocationResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationRemote.invoke(Unit).collectResource(
                onSuccess = { locationResponse ->
                    updateState {
                        copy(
                            region = region.copy(value = locationResponse?.currentRegionName.toString()),
                            area = area.copy(value = locationResponse?.currentAreaName.toString()),
                            regionId = locationResponse?.currentRegion.toString(),
                            areaId = locationResponse?.currentArea.toString()
                        )
                    }
                })
        }
    }

    private fun getSavedLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                getLocationLocal.invoke(Unit).collectResource(
                    onSuccess = { latlng ->
                        updateState {
                            copy(
                                latitude = latlng?.latitude.toString(),
                                longitude = latlng?.longitude.toString()
                            )
                        }
                    })
            }

        }
    }

    private fun onSaveAddressClicked() {
        val request = AddAddressRequest(
            firstPhoneNumber = state.value.firstPhone.value,
            secondPhoneNumber = state.value.secondPhone.value,
            floorNumber = state.value.floorNumber.value,
            countryId = 67,
            apartmentNumber = state.value.apartmentNumber.value,
            buildingNumber = state.value.buildingNumber.value,
            specialSign = state.value.specialSign.value,
            street = state.value.street.value,
            regionId = state.value.regionId.toInt(),
            areaId = state.value.areaId.toInt(),
            latitude = state.value.latitude,
            longitude = state.value.longitude
        )
        viewModelScope.launch(Dispatchers.IO) {
            saveAddress.invoke(request).collectResource(
                onSuccess = ::onSaveAddressSuccess, onLoading = ::onLoading
            )
        }

    }

    fun onSaveAddressSuccess(saveAddressResponse: Address) {
        updateState { copy() }
        when (route.addressType) {
            AddressType.SENDER -> {
                saveSenderAddress(saveAddressResponse)
            }

            AddressType.RECEIVER -> {
                viewModelScope.launch(Dispatchers.IO) {
                    saveRecipientAddress(saveAddressResponse).collectResource()
                }
            }
        }

        fireNavigate(destination = IMainGraph.Home, builder = {
            popUpTo(IMainGraph.Home) {
                inclusive = false
                saveState = false
            }
            launchSingleTop = true
        })


    }

    private fun saveSenderAddress(address: Address) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSenderAddress.invoke(address).collectResource()
        }
    }

    fun onLoading(isLoad: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoad))
    }


    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            firstPhone = firstPhone.copy(error = errors[ErrorKeyEnum.ADDRESS_FIRST_PHONE]),
            secondPhone = secondPhone.copy(error = errors[ErrorKeyEnum.ADDRESS_SECOND_PHONE]),
        )
    }
}