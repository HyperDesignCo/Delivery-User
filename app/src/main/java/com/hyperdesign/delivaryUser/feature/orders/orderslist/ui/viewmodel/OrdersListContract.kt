package com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.viewmodel

import com.hyperdesign.delivaryUser.common.ui.extension.formatDate
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.hyperdesign.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus
import com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersContract.DeliveryUiState
import com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.viewmodel.OrdersContract.OrderUiState

sealed interface OrdersContract {
    sealed interface Action {
        data class OrderClicked(val id: Int) : Action
        data object OnBackClicked : Action
        data class OnOpenDialogClicked(val id: Int) : Action
        data object OnCloseDialogClicked : Action
        data class OnRatingClicked(val index : Int) : Action
        data class OnCommentChanged(val comment : String ): Action
        data object OnSendRateClicked: Action
        data object Init :Action
    }

    data class State(
        val orders: List<OrderUiState> = emptyList(),
        val rate: RateDialogUiState = RateDialogUiState(),
    )

    data class OrderUiState(
        val orderId: Int = 0,
        val orderState: OrderStatus = OrderStatus.PENDING,
        val orderDate: String = "",
        val orderPrice: String = "",
        val delivary: DeliveryUiState = DeliveryUiState(),
        val provider: String = "",
        val stars: String = "",
    )

    data class DeliveryUiState(
        val deliveryName: String = "",
        val deliveryImage: String = "",
    )

    data class RateDialogUiState(
        val isDialogVisible: Boolean = false,
        val orderId: Int = 0,
        val comment: String = "",
        val rate : Int = 5
    )
}

fun Order.toUiState() = OrderUiState(
    orderId = this.id,
    orderState = this.orderStatus,
    orderDate = this.createdAt.formatDate(),
    orderPrice = this.orderPrice,
    delivary = DeliveryUiState(
        deliveryName = this.deliveryName,
        deliveryImage = this.deliveryImage,
    ),
    provider = this.userName,
    stars = if(this.deliveryRate == "0.0") "0" else this.deliveryRate
)