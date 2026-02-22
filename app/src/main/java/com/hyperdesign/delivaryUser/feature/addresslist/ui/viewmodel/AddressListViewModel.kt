package com.hyperdesign.delivaryUser.feature.addresslist.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hyperdesign.delivaryUser.common.ui.navigation.IOrderGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.hyperdesign.delivaryUser.service.address.domain.interactors.GetAddressesUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.models.domain.Address
import kotlinx.coroutines.launch

class AddressListViewModel(
    savedStateHandle: SavedStateHandle,
    private val addresses: GetAddressesUseCase,
    private val saveSenderAddress: SaveSenderAddressUseCase,
    private val saveRecipientAddress: SaveRecipientAddressUseCase,
) :
    BaseViewModel<AddressListContract.State, AddressListContract.Action>(AddressListContract.State()) {
    val route = savedStateHandle.toRoute<IOrderGraph.AddressList>()

    init {
        getAddresses()
    }

    override fun onActionTrigger(action: AddressListContract.Action) {
        when (action) {
            is AddressListContract.Action.SaveAddress -> saveAddress()
            is AddressListContract.Action.SelectAddress -> selectAddress(action.address)
            is AddressListContract.Action.OnBackClicked -> onBackClicked()
        }
    }

    private fun getAddresses() {
        viewModelScope.launch {
            addresses.invoke(Unit).collectResource(onSuccess = ::onGetAddressesSuccess)
        }
    }

    private fun onGetAddressesSuccess(addresses: List<Address>) {
        updateState {
            copy(addressList = addresses.map {
                AddressListContract.AddressUiState(
                    address = it,
                    isSelected = it.id == route.id
                )
            })
        }
    }


    private fun saveAddress() {
        when (route.addressType) {
            AddressType.SENDER -> saveSenderAddress()
            AddressType.RECEIVER -> saveRecipientAddress()
        }
    }

    private fun saveSenderAddress() {
        viewModelScope.launch {
            saveSenderAddress.invoke(state.value.selectedAddress.address).collectResource(onSuccess = {
                fireNavigateUp()
            })
        }
    }

    private fun saveRecipientAddress() {
        viewModelScope.launch {
            saveRecipientAddress.invoke(state.value.selectedAddress.address).collectResource(onSuccess = {
                fireNavigateUp()
            })
        }
    }

    private fun selectAddress(address: AddressListContract.AddressUiState) {
        updateState {
            copy(
                selectedAddress = address,
                addressList = addressList.map { it.copy(isSelected = it == address) })
        }
    }

    private fun onBackClicked() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}