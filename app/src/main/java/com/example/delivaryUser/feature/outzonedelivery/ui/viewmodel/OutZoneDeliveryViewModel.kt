package com.example.delivaryUser.feature.outzonedelivery.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IAddressGraph
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.outzonedelivery.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.outzonedelivery.domain.OpenDeliveryZone
import com.example.delivaryUser.feature.outzonedelivery.domain.interactors.AddAreaRequestUseCase
import com.example.delivaryUser.feature.outzonedelivery.domain.model.AddAreaResponse
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OutZoneDeliveryViewModel(
    savedStateHandle: SavedStateHandle,
    private val addAreaRequestUseCase: AddAreaRequestUseCase,
    private val saveLocationUseCase: SaveLocationUseCase
) : BaseViewModel<OutZoneDeliveryContract.State, OutZoneDeliveryContract.Action>(
    OutZoneDeliveryContract.State()
) {

    private val route = savedStateHandle.toRoute<IMainGraph.OutSideZoneDelivery>()


    override fun onActionTrigger(action: OutZoneDeliveryContract.Action) {
        when (action) {
            OutZoneDeliveryContract.Action.OnChangeLocation -> {

                onNavigateToMap()
            }

            OutZoneDeliveryContract.Action.OnVoteClickAction -> {
                onVoteDeliveryArea()
            }

            OutZoneDeliveryContract.Action.OnBackClick -> {
                onBackClick()
            }
        }
    }

    private fun onBackClick() {
        viewModelScope.launch(Dispatchers.IO) {
            fireNavigateUp()
        }
    }

    private fun onNavigateToMap() {
        when (route.openDeliveryZone) {
            OpenDeliveryZone.MAP_SCREEN -> {
                viewModelScope.launch {
                    fireNavigateUp()
                }
            }

            OpenDeliveryZone.HOME_SCREEN -> {
                fireNavigate(IAddressGraph.Map)
            }
        }
    }

    private fun onVoteDeliveryArea() {
        val request = AddAreaRequest(latitude = route.lat, longitude = route.lng)
        val latLng = LatLng(route.lat.toDouble(),route.lng.toDouble())
        Log.d("location","Latling $latLng")
        saveLocation(latLng)
        viewModelScope.launch(Dispatchers.IO) {
            addAreaRequestUseCase.invoke(request).collectResource(
                onSuccess = ::getResponseOfAddAreaDeliveryRequest, onLoading = ::loading
            )
        }

    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocationUseCase.invoke(body = latLng).collectResource()
        }
    }

    fun getResponseOfAddAreaDeliveryRequest(addAreaResponse: AddAreaResponse) {
        updateState {
            copy(addAreaRequestResponse = addAreaResponse)
        }
        fireMessage(IMessageEvent.Toast(UIText.DynamicString(value = addAreaResponse.message)))
        fireNavigate(destination = IMainGraph.Home,builder = {
            popUpTo(IMainGraph.Home) {
                inclusive = false
                saveState = false
            }
            launchSingleTop = true
        })

    }

    fun loading(isLoading: Boolean) {
        fireLoading(ILoadingEvent.CircularProgressIndicator(isLoading))

    }


}