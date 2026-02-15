package com.example.delivaryUser.feature.fastorder.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.common.data.repository.remote.File
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.message.MessageType
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.example.delivaryUser.feature.fastorder.data.models.request.FastOrderRequest
import com.example.delivaryUser.feature.fastorder.domain.interactors.CreateFastOrderUseCase
import com.example.delivaryUser.service.address.domain.interactors.GetSenderAddress
import com.example.delivaryUser.service.address.domain.models.domain.Address
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.example.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.launch

class FastOrderViewModel(
    val currentAddress: GetSavedLocationUseCase,
    val checkLocation: CheckLocationUseCase,
    val getLocation: GetSenderAddress,
    val checkOut: CreateFastOrderUseCase,
) :
    BaseViewModel<FastOrderContract.State, FastOrderContract.Action>(FastOrderContract.State()) {
    companion object {
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }

    override fun onActionTrigger(action: FastOrderContract.Action) {
        when (action) {
            is FastOrderContract.Action.Init -> init()
            is FastOrderContract.Action.OnOrderDetailsChanged -> orderDetailsChanged(action.orderDetails)
            is FastOrderContract.Action.ImageSelectionAction.SelectFile -> onFileSelected(
                imageFile = action.imageFile,
                imageUri = action.imageUri
            )

            is FastOrderContract.Action.OnImageRemoveClicked -> onImageRemovedClicked(action.index)
            is FastOrderContract.Action.OnCheckOutClicked -> onCheckOutClicked()
        }
    }

    private fun init() {
        getCurrentAddress()
        getLocation()
    }

    private fun getCurrentAddress() {
        viewModelScope.launch {
            currentAddress.invoke(body = Unit).collectResource(onSuccess = {
                longitude = it?.longitude.orEmpty()
                latitude = it?.latitude.orEmpty()
                checkLocation(
                    latitude = it?.latitude.orEmpty(),
                    longitude = it?.longitude.orEmpty()
                )
            })
        }
    }

    private fun checkLocation(latitude: Double, longitude: Double) {
        val request = CheckLocationRequest(latitude = latitude.toString(), longitude = longitude.toString())
        viewModelScope.launch {
            checkLocation.invoke(body = request).collectResource(onSuccess = {
                onCheckLocationSuccess(location = it.data)
            })
        }
    }

    private fun onCheckLocationSuccess(location: Location) {
        if (location.currentRegion.isEmpty() || location.currentArea.isEmpty()) {
            fireNavigate(
                IMainGraph.DeliveryOutZone(
                    latitude = latitude,
                    longitude = longitude,
                )
            )
            updateState {
                copy(deliveryCost = deliveryCost)
            }
        }
    }

    private fun getLocation() {
        viewModelScope.launch {
            getLocation.invoke(body = Unit).collectResource(onSuccess = {
                displaySavedLocation(
                    it
                )
            })
        }
    }

    private fun displaySavedLocation(savedLocation: Address) {
        val regionName = savedLocation.region
        val areaName = savedLocation.area
        val displayLocation = "$regionName,$areaName"
        updateState {
            copy(location = displayLocation, addressId = savedLocation.id)
        }
    }

    private fun orderDetailsChanged(orderDetails: String) {
        updateState {
            copy(orderDetails = orderDetails)
        }
    }

    private fun onFileSelected(imageUri: Uri, imageFile: File) {
        updateState {
            val newImages = images.toMutableList().apply {
                add(
                    FastOrderContract.ImageState(
                        image = imageUri.toString(),
                        imageFile = imageFile
                    )
                )
            }
            copy(images = newImages)
        }
    }

    private fun onImageRemovedClicked(index: Int) {
        updateState {
            val newImages = images.toMutableList().apply {
                removeAt(index)
            }
            copy(images = newImages)
        }
    }

    private fun onCheckOutClicked() {
        val request = FastOrderRequest(
            addressId = state.value.addressId,
            note = state.value.orderDetails,
            endAddressId = state.value.addressId,
            images = state.value.images.map { it.imageFile },
            deliveryCost = state.value.deliveryCost,
        )
        viewModelScope.launch {
            checkOut.invoke(body = request).collectResource(onSuccess = {
                fireMessage(
                    IMessageEvent.Snackbar(
                        message = UIText.StringResource(R.string.we_received_order),
                        messageType = MessageType.SUCCESS
                    )
                )
                fireNavigateUp()
            })
        }
    }
}