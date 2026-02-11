package com.example.delivaryUser.feature.cancelorder.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.delivaryUser.R
import com.example.delivaryUser.common.data.repository.remote.File
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.message.MessageType
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.example.delivaryUser.feature.cancelorder.domain.interactors.CancelOrderUseCase
import kotlinx.coroutines.launch

class CancelOrderViewModel(
    saveStateHandle: SavedStateHandle,
    private val cancelOrder: CancelOrderUseCase,
) :
    BaseViewModel<CancelOrderContract.State, CancelOrderContract.Action>(CancelOrderContract.State()) {
    val route = saveStateHandle.toRoute<IMainGraph.CancelOrder>()
    override fun onActionTrigger(action: CancelOrderContract.Action) {
        when (action) {
            is CancelOrderContract.Action.ImageSelectionAction.SelectFile -> onFileSelected(
                imageUri = action.imageUri,
                imageFile = action.imageFile
            )

            is CancelOrderContract.Action.OnCommentChanged -> onCommentChanged(comment = action.comment)
            is CancelOrderContract.Action.OnCancelOrderClicked -> onCancelOrderClicked()
            is CancelOrderContract.Action.OpenDialog -> openDialog()
            is CancelOrderContract.Action.CloseDialog -> closeDialog()
            is CancelOrderContract.Action.OnBackClicked -> onBackClicked()
        }
    }

    private fun onFileSelected(imageUri: Uri, imageFile: File) =
        updateState { copy(image = image.copy(value = imageUri.toString(), error = null), imageFile = imageFile) }

    private fun onCommentChanged(comment: String) = updateState { copy(comment = comment) }
    private fun onCancelOrderClicked() {
        val request = CancelOrderRequest(
            orderId = route.id,
            cancelReason = state.value.comment,
            cancelImage = state.value.imageFile
        )
        viewModelScope.launch {
            cancelOrder.invoke(body = request).collectResource(onSuccess = {
                fireMessage(
                    IMessageEvent.Snackbar(
                        message = UIText.StringResource(R.string.order_canceled),
                        messageType = MessageType.SUCCESS
                    )
                )
                closeDialog()
                fireNavigateUp()
            })
        }
    }

    private fun openDialog() = updateState { copy(isDialogVisible = true) }
    private fun closeDialog() = updateState { copy(isDialogVisible = false) }
    private fun onBackClicked() = viewModelScope.launch { fireNavigateUp() }
}