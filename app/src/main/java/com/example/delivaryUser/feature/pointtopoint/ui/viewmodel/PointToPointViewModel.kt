package com.example.delivaryUser.feature.pointtopoint.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.pointtopoint.data.models.request.DeliveryCostRequest
import com.example.delivaryUser.feature.pointtopoint.data.models.request.PointToPointRequest
import com.example.delivaryUser.feature.pointtopoint.domain.model.OrderPurpose
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.AddPointToPointOrderUseCase
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.GetDeliveryCostUseCase
import com.example.delivaryUser.feature.pointtopoint.domain.usecase.GetOrderPurposesUseCase
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.example.delivaryUser.service.address.domain.interactors.GetRecipientAddressUseCase
import com.example.delivaryUser.service.address.domain.interactors.GetSenderAddress
import com.example.delivaryUser.service.address.domain.models.domain.Address
import kotlinx.coroutines.launch

class PointToPointViewModel(
    private val senderAddress: GetSenderAddress,
    private val recipientAddress: GetRecipientAddressUseCase,
    private val addPointToPointOrderUseCase: AddPointToPointOrderUseCase,
    private val orderPurposes: GetOrderPurposesUseCase,
    private val deliveryCost: GetDeliveryCostUseCase,
) :
    BaseViewModel<PointToPointContract.State, PointToPointContract.Action>(PointToPointContract.State()) {

    init {
        getSenderAddress()
        getReceiverAddress()
        getOrderPurposes()
    }

    fun refresh() {
        getSenderAddress()
        getReceiverAddress()
        getOrderPurposes()
    }

    override fun onActionTrigger(action: PointToPointContract.Action) {
        when (action) {
            is PointToPointContract.Action.AddReceiverAddress -> addReceiverAddress()
            is PointToPointContract.Action.AddSenderAddress -> addSenderAddress()
            is PointToPointContract.Action.ChooseReceiverAddress -> chooseReceiverAddress()
            is PointToPointContract.Action.ChooseSenderAddress -> chooseSenderAddress()
            is PointToPointContract.Action.ExpandDropDownMenu -> expandDropDownMenu()
            is PointToPointContract.Action.OnCheckOutClicked -> onCheckOutClicked()
            is PointToPointContract.Action.ChooseOrderType -> chooseOrderType(action.id)
            is PointToPointContract.Action.OnEstimatedPriceChange -> onEstimatedPriceChanged(action.price)
            is PointToPointContract.Action.OnCommentChange -> onCommentChanges(action.comment)
            is PointToPointContract.Action.DismissDropDownMenu -> dismissMenu()
            is PointToPointContract.Action.OnBackClicked -> navigateBack()
        }
    }

    private fun getSenderAddress() {
        viewModelScope.launch {
            senderAddress.invoke(body = Unit).collectResource(onSuccess = ::onGetSenderAddressSuccess)
        }
    }

    private fun onGetSenderAddressSuccess(address: Address) {
        updateState { copy(senderAddress = senderAddress.copy(address = address)) }
        if (checkNotEmpty()) getDeliveryCost()
    }

    private fun getReceiverAddress() {
        viewModelScope.launch {
            recipientAddress.invoke(body = Unit).collectResource(onSuccess = ::onGetReceiverAddressSuccess)
        }
    }

    private fun onGetReceiverAddressSuccess(address: Address) {
        updateState { copy(receiverAddress = receiverAddress.copy(address = address)) }
        if (checkNotEmpty()) getDeliveryCost()
    }

    private fun getDeliveryCost() {
        val request = DeliveryCostRequest(
            senderAddressId = state.value.senderAddress.address.id.toString(),
            recipientAddressId = state.value.receiverAddress.address.id.toString()
        )
        viewModelScope.launch {
            deliveryCost.invoke(body = request)
                .collectResource(onSuccess = { updateState { copy(deliveryFees = it.deliveryCost) } })
        }
    }

    private fun getOrderPurposes() {
        viewModelScope.launch {
            orderPurposes.invoke(body = Unit).collectResource(onSuccess = ::onGetOrderPurposesSuccess)
        }
    }

    private fun onGetOrderPurposesSuccess(orderPurposes: List<OrderPurpose>) {
        updateState {
            copy(
                orderTypes = orderPurposes.mapIndexed { index, purpose ->
                    PointToPointContract.OrderPurposeUiState(
                        orderPurpose = purpose,
                        isSelected = index == 0
                    )
                },
                selectedOrderType = selectedOrderType.copy(value = orderPurposes.first().name)
            )
        }
    }

    private fun checkNotEmpty(): Boolean =
        state.value.senderAddress.address.id != 0 && state.value.receiverAddress.address.id != 0

    private fun addReceiverAddress() {
        fireNavigate(IOrderGraph.Map(AddressType.RECEIVER))
    }

    private fun addSenderAddress() {
        fireNavigate(IOrderGraph.Map(AddressType.SENDER))
    }

    private fun chooseReceiverAddress() {
        fireNavigate(
            destination = IOrderGraph.AddressList(
                addressType = AddressType.RECEIVER, id = if (state.value.receiverAddress.address.id != 0) {
                    state.value.receiverAddress.address.id
                } else {
                    null
                }
            ),
        )
    }

    private fun chooseSenderAddress() {
        fireNavigate(
            destination = IOrderGraph.AddressList(
                addressType = AddressType.SENDER, id = if (state.value.senderAddress.address.id != 0) {
                    state.value.senderAddress.address.id
                } else {
                    null
                }
            )
        )
    }

    private fun expandDropDownMenu() {
        updateState { copy(isExpanded = isExpanded.not()) }
    }

    private fun onCheckOutClicked() {
        val currentState = state.value

        val selectedOrderPurpose = currentState.orderTypes
            .first { it.isSelected }.orderPurpose

        val request = PointToPointRequest(
            orderPurposeId = selectedOrderPurpose.id,
            senderAddressId = currentState.senderAddress.address.id,
            recipientAddressId = currentState.receiverAddress.address.id,
            note = currentState.comment.value,
            orderEstimatedPrice = if (currentState.estimatedPrice.value.isNotBlank()) currentState.estimatedPrice.value.toDouble() else 0.0,
            deliveryCost = currentState.deliveryFees,
            orderType = "2"
        )
        viewModelScope.launch {
            addPointToPointOrderUseCase.invoke(body = request).collectResource(onSuccess = {
                fireNavigate(destination = IMainGraph.Home)
            })
        }
    }

    private fun chooseOrderType(id: Int) {
        updateState {
            copy(
                orderTypes = orderTypes.map { item ->
                    item.copy(
                        isSelected = item.orderPurpose.id == id
                    )
                },
                selectedOrderType = selectedOrderType.copy(value = orderTypes.first { it.orderPurpose.id == id }.orderPurpose.name)
            )
        }
    }

    private fun onEstimatedPriceChanged(price: String) {
        updateState { copy(estimatedPrice = estimatedPrice.copy(value = price)) }
    }

    private fun onCommentChanges(commentValue: String) {
        updateState { copy(comment = comment.copy(value = commentValue)) }
    }

    private fun dismissMenu() {
        updateState { copy(isExpanded = false) }
    }

    private fun navigateBack() {
        fireNavigate(IMainGraph.Home) {
            popUpTo(IOrderGraph.PointToPoint) {
                inclusive = true
                saveState = false
            }
        }
    }
}