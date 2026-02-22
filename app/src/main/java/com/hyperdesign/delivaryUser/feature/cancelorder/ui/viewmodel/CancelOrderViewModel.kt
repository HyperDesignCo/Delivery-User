package com.hyperdesign.delivaryUser.feature.cancelorder.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.ui.extension.UIText
import com.hyperdesign.delivaryUser.common.ui.message.IMessageEvent
import com.hyperdesign.delivaryUser.common.ui.message.MessageType
import com.hyperdesign.delivaryUser.common.ui.navigation.IMainGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.cancelorder.data.models.request.CancelOrderRequest
import com.hyperdesign.delivaryUser.feature.cancelorder.domain.interactors.CancelOrderUseCase
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
                fireNavigate(IMainGraph.Home, builder = {
                    popUpTo(0) {
                        saveState = true
                        inclusive = true
                    }
                })
            })
        }
    }

    private fun openDialog() = updateState { copy(isDialogVisible = true) }
    private fun closeDialog() = updateState { copy(isDialogVisible = false) }
    private fun onBackClicked() = viewModelScope.launch { fireNavigateUp() }
}