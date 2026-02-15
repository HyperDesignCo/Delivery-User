package com.example.delivaryUser.feature.chatwithai.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.example.delivaryUser.feature.chatwithai.data.models.request.ChatWithAiRequest
import com.example.delivaryUser.feature.chatwithai.domain.interactors.ChatWithAiUseCase
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.example.delivaryUser.service.location.domain.model.Location
import kotlinx.coroutines.launch

class ChatWithAiViewModel(
    private val currentAddress: GetSavedLocationUseCase,
    val checkLocation: CheckLocationUseCase,
    private val chatWithAi: ChatWithAiUseCase,
) :
    BaseViewModel<ChatWithAiContract.State, ChatWithAiContract.Action>(ChatWithAiContract.State()) {

    companion object {
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }

    override fun onActionTrigger(action: ChatWithAiContract.Action) {
        when (action) {
            is ChatWithAiContract.Action.Init -> getCurrentAddress()
            is ChatWithAiContract.Action.OnBackClicked -> onBackClicked()
        }
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
        } else {
            getChatWithAi(latitude = latitude, longitude = longitude)
        }
    }

    private fun getChatWithAi(latitude: Double, longitude: Double) {
        val request = ChatWithAiRequest(latitude = latitude, longitude = longitude)
        viewModelScope.launch {
            chatWithAi.invoke(body = request).collectResource(onSuccess = ::onGetChatWithAiSuccess)
        }
    }

    private fun onGetChatWithAiSuccess(link: String) {
        updateState {
            copy(link = link)
        }
    }

    private fun onBackClicked() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}