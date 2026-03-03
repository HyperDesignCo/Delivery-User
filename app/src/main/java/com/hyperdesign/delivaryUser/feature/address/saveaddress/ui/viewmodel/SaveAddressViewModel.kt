package com.hyperdesign.delivaryUser.feature.address.saveaddress.ui.viewmodel


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hyperdesign.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.hyperdesign.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.loading.ILoadingEvent
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IOrderGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.saveaddress.domain.interactors.SaveAddressUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.hyperdesign.delivaryUser.service.address.data.models.request.AddAddressRequest
import com.hyperdesign.delivaryUser.service.address.domain.interactors.GetAddressesUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.models.domain.Address
import com.hyperdesign.delivaryUser.service.location.domain.interactors.GetLocationCheckUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.feature.address.mapview.ui.component.SaveAddressDestinationType
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
class SaveAddressViewModel(
    private val saveAddress: SaveAddressUseCase,
    private val getLocationRemote: GetLocationCheckUseCase,
    private val getLocationLocal: GetSavedLocationUseCase,
    private val saveSenderAddress: SaveSenderAddressUseCase,
    savedStateHandle: SavedStateHandle,
    private val saveRecipientAddress: SaveRecipientAddressUseCase,
    private val getAddress: GetAddressesUseCase
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
            is SaveAddressContract.Action.OnApartmentNumberChanged -> {
                onApartmentNumberChanged(action.apartmentNumber)
            }

            is SaveAddressContract.Action.OnBuildingNumberChanged -> {
                onBuildingNumberChanged(action.buildingNumber)
            }

            is SaveAddressContract.Action.OnExtraInfoChanged -> {
                onExtraInfoChanged(action.extraInfo)
            }

            is SaveAddressContract.Action.OnFloorNumberChanged -> {
                onFloorNumberChanged(action.floorNumber)
            }

            is SaveAddressContract.Action.OnFirstPhoneChanged -> {
                onFirstPhoneChanged(action.phone)
            }

            is SaveAddressContract.Action.OnSecondPhoneChanged -> {
                onSecondPhoneChanged(action.phone)
            }

            is SaveAddressContract.Action.OnSpecialSignChanged -> {
                onSpecialSignChanged(action.specialSign)
            }

            is SaveAddressContract.Action.OnStreetChanged -> {
                onStreetChanged(action.street)
            }

            is SaveAddressContract.Action.OnSaveAddressClicked -> {
                onSaveAddressClicked()
            }

            is SaveAddressContract.Action.OnBackClick -> {
                onBackClick()
            }
        }
    }

    private fun onApartmentNumberChanged(value: String) {
        updateState {
            copy(
                apartmentNumber = apartmentNumber.copy(value = value),
                isAddressExist = false
            )
        }
    }

    private fun onBuildingNumberChanged(value: String) {
        updateState {
            copy(
                buildingNumber = buildingNumber.copy(value = value),
                isAddressExist = false
            )
        }
    }

    private fun onExtraInfoChanged(value: String) {
        updateState {
            copy(
                extraInfo = extraInfo.copy(value = value),
                isAddressExist = false
            )
        }
    }

    private fun onFloorNumberChanged(value: String) {
        updateState {
            copy(
                floorNumber = floorNumber.copy(value = value),
                isAddressExist = false
            )
        }
    }

    private fun onFirstPhoneChanged(value: String) {
        updateState {
            copy(firstPhone = firstPhone.copy(value = value, error = null), isAddressExist = false)
        }
    }

    private fun onSecondPhoneChanged(value: String) {
        updateState {
            copy(secondPhone = secondPhone.copy(value = value, error = null), isAddressExist = false)
        }
    }

    private fun onSpecialSignChanged(value: String) {
        updateState {
            copy(
                specialSign = specialSign.copy(value = value),
                isAddressExist = false
            )
        }
    }

    private fun onStreetChanged(value: String) {
        updateState {
            copy(
                street = street.copy(value = value),
                isAddressExist = false
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
                            region = region.copy(value = locationResponse.currentRegionName),
                            area = area.copy(value = locationResponse.currentAreaName),
                            regionId = locationResponse.currentRegion,
                            areaId = locationResponse.currentArea
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
            if (addressIsExist()) {
                updateState { copy(isAddressExist = true) }
                fireMessage(
                    IMessageEvent.Snackbar(
                        UIText.StringResource(R.string.your_address_already_saved),
                        messageType = MessageType.ERROR
                    )
                )
            } else {
                saveAddress.invoke(request).collectResource(
                    onSuccess = ::onSaveAddressSuccess,
                    onLoading = ::onLoading
                )
            }
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

        when(route.saveAddressDestinationType){

            SaveAddressDestinationType.MAP_DESTINATION -> {
                fireNavigate(destination = IMainGraph.Home, builder = {
                    popUpTo(IMainGraph.Home) {
                        inclusive = true
                        saveState = false
                    }
                })
            }
            SaveAddressDestinationType.POINT_TO_POINT_DESTINATION -> {
                fireNavigate(destination = IOrderGraph.PointToPoint, builder = {
                    popUpTo(IOrderGraph.PointToPoint) {
                        inclusive = true
                        saveState = false
                    }
                })
            }
        }

    }

    private fun saveSenderAddress(address: Address) {
        viewModelScope.launch(Dispatchers.IO) {
            saveSenderAddress.invoke(address).collectResource()
        }
    }

    fun onLoading(isLoad: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoad))
    }

    private suspend fun addressIsExist(): Boolean = suspendCoroutine { continuation ->
        viewModelScope.launch(Dispatchers.IO) {
            getAddress.invoke(Unit).collectResource(
                onSuccess = { addresses ->
                    val exists = addresses.any {
                        it.regionId == state.value.regionId &&
                        it.areaId == state.value.areaId &&
                        it.street == state.value.street.value &&
                        it.floorNumber == state.value.floorNumber.value &&
                        it.buildingNumber == state.value.buildingNumber.value &&
                        it.apartmentNumber == state.value.apartmentNumber.value &&
                        it.firstPhone == state.value.firstPhone.value &&
                        it.secondPhone == state.value.secondPhone.value &&
                        it.specialSign == state.value.specialSign.value
                    }
                    continuation.resume(exists)
                }
            )
        }
    }


    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            firstPhone = firstPhone.copy(error = errors[ErrorKeyEnum.ADDRESS_FIRST_PHONE]),
            secondPhone = secondPhone.copy(error = errors[ErrorKeyEnum.ADDRESS_SECOND_PHONE]),
        )
    }
}