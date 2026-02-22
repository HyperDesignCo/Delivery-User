package com.example.delivaryUser.feature.home.ui.viewmodel

import com.example.delivaryUser.feature.home.domain.models.Ad
import com.example.delivaryUser.feature.home.ui.viewmodel.HomeContract.AdUiState
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.example.delivaryUser.service.location.domain.model.CheckLocation
import com.google.android.gms.maps.model.LatLng

sealed interface HomeContract {
    sealed interface Action {
        data object FastOrderClicked : Action
        data object ChatWithAiClicked : Action
        data object NavigateBacKClicked : Action
        data class OnLocationClicked(val location: String) : Action
        data object OnAddLocationClicked : Action
        data object OnNewOrderClicked : Action
        data object OnPointToPointClicked : Action
        data class OnChangeLocation(val latLng: LatLng) : Action
        data class OnTrackOrderClicked(val id: Int) : Action
    }

    data class State(
        val location: String = "",
        val ads: List<AdUiState> = emptyList(),
        val isButtonsVisible: Boolean = false,
        val latLng: LatLng? = null,
        val savedLatLng: LatLng? = null,
        val trackOrders: List<TrackOrderItem> = emptyList(),
        val checkLocationResponse: CheckLocation? = null,
    )

    data class AdUiState(
        val id: String = "",
        val image: String = "",
    )
    data class TrackOrderItem(
        val orderStatus: OrderStatus = OrderStatus.PENDING,
        val orderId: String = "",
        val image: String = "",
        val deliveryName: String = "",
    )
}

fun Ad.toUiState() = AdUiState(
    id = this.id.toString(),
    image = this.image,
)
fun Order.toUiState() = HomeContract.TrackOrderItem(
    orderStatus = orderStatus,
    orderId = id.toString(),
    image = deliveryImage,
    deliveryName = deliveryName
)