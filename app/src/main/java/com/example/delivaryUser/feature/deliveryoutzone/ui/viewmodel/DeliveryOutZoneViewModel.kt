package com.example.delivaryUser.feature.deliveryoutzone.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.deliveryoutzone.data.model.request.AddAreaRequest
import com.example.delivaryUser.feature.deliveryoutzone.domain.interactors.AddAreaUseCase
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeliveryOutZoneViewModel(
    savedStateHandle: SavedStateHandle,
    private val addArea: AddAreaUseCase,
    private val saveLocation: SaveLocationUseCase,
) : BaseViewModel<Unit, DeliveryOutZoneContract.Action>(Unit) {
    private val route = savedStateHandle.toRoute<IMainGraph.DeliveryOutZone>()

    override fun onActionTrigger(action: DeliveryOutZoneContract.Action) {
        when (action) {
            is DeliveryOutZoneContract.Action.OnChangeLocationClicked -> {
                onChangeLocationClicked()
            }

            is DeliveryOutZoneContract.Action.OnVoteClicked -> {
                onVoteClicked()
            }
        }
    }

    private fun onChangeLocationClicked() {
        fireNavigate(destination = IOrderGraph.Map(), builder = {
            popUpTo(IOrderGraph.Map()) {
                inclusive = true
            }
        })
    }

    private fun onVoteClicked() {
        val request = AddAreaRequest(latitude = route.latitude.toString(), longitude = route.longitude.toString())
        viewModelScope.launch(Dispatchers.IO) {
            addArea.invoke(request)
                .collectResource(
                    onSuccess = { onAddAreaSuccess() },
                    onLoading = { fireLoading(ILoadingEvent.CircularProgressIndicator(it)) })
        }
    }

    private fun onAddAreaSuccess() {
        saveLocation(latLng = LatLng(route.latitude, route.longitude))
        fireNavigate(destination = IMainGraph.Home, builder = {
            popUpTo(IMainGraph.Home) {
                inclusive = true
                saveState = false
            }
        })
    }

    private fun saveLocation(latLng: LatLng) {
        viewModelScope.launch {
            saveLocation.invoke(body = latLng).collectResource()
        }
    }
}