package com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel

import android.net.http.SslCertificate.saveState
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IAddressGraph
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetSavedLocationUseCase
import com.example.delivaryUser.feature.address.saveaddress.data.model.request.AddAddressRequest
import com.example.delivaryUser.feature.address.saveaddress.domain.interactors.AddAddressUseCase
import com.example.delivaryUser.feature.address.saveaddress.domain.model.SaveAddress
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.example.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.example.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveAddressViewModel(
    private val addAddressUseCase: AddAddressUseCase,
    private val getLocationResponseUseCase: GetLocationResponseUseCase,
    private val getSavedLocationUseCase: GetSavedLocationUseCase,
    private val saveSenderAddressUseCase: SaveSenderAddressUseCase,
    savedStateHandle: SavedStateHandle,
    private val saveRecipientAddressUseCase: SaveRecipientAddressUseCase
) : BaseViewModel<SaveAddressContract.State, SaveAddressContract.Action>(
    SaveAddressContract.State()
) {

    private val route = savedStateHandle.toRoute<IAddressGraph.SaveAddress>()

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

            is SaveAddressContract.Action.OnPhone1Changed -> {
                onPhone1Changed(action.phone1)
            }

            is SaveAddressContract.Action.OnPhone2Changed -> {
                onPhone2Changed(action.phone2)
            }

            SaveAddressContract.Action.OnSaveAddressClicked -> {
                onSaveAddressClicked()
            }

            is SaveAddressContract.Action.OnSpecialSignChanged -> {
                onSpecialSignChanged(action.specialSign)
            }

            is SaveAddressContract.Action.OnStreetChanged -> {
                onStreetChanged(action.street)
            }

            SaveAddressContract.Action.OnBackClick -> {
                onBackClick()
            }
        }
    }

    private fun onBackClick() {
        viewModelScope.launch(Dispatchers.IO) {
            fireNavigateUp()
        }

    }

    private fun onStreetChanged(value: String) {
        updateState {
            copy(
                street = street.copy(value = value)
            )
        }
    }

    private fun getLocationResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationResponseUseCase.invoke(Unit).collectResource(
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
                getSavedLocationUseCase.invoke(Unit).collectResource(
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


    private fun onSpecialSignChanged(value: String) {
        updateState {
            copy(
                specialSign = specialSign.copy(value = value)
            )
        }
    }

    private fun onSaveAddressClicked() {
        val request = AddAddressRequest(
            phone1 = state.value.phone1.value,
            phone2 = state.value.phone2.value,
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
            addAddressUseCase.invoke(request).collectResource(
                onSuccess = ::onAddAddressSuccess, onLoading = ::onLoading
            )
        }

    }

    fun onAddAddressSuccess(saveAddressResponse: SaveAddress) {
        fireMessage(IMessageEvent.Toast(UIText.DynamicString(value = saveAddressResponse.message)))
        updateState {
            copy()
        }
        when (route.addressType) {
            AddressType.SENDER -> {
                saveSenderAddress(saveAddressResponse.address)
            }

            AddressType.RECEIVER -> {
                viewModelScope.launch(Dispatchers.IO) {
                    saveRecipientAddressUseCase(saveAddressResponse.address)
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
            saveSenderAddressUseCase.invoke(address).collectResource()
        }
    }

    fun onLoading(isLoad: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoad))
    }

    private fun onPhone2Changed(value: String) {
        updateState {
            copy(
                phone2 = phone2.copy(value = value, error = null)
            )
        }
    }

    private fun onPhone1Changed(value: String) {
        updateState {
            copy(
                phone1 = phone1.copy(value = value, error = null)
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

    private fun onExtraInfoChanged(value: String) {
        updateState {
            copy(
                extraInfo = extraInfo.copy(value = value)
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

    private fun onApartmentNumberChanged(value: String) {
        updateState {
            copy(
                apartmentNumber = apartmentNumber.copy(value = value)
            )
        }
    }

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            phone1 = phone1.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
            phone2 = phone2.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
        )
    }
}