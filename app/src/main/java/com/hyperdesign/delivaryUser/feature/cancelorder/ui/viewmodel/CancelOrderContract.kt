package com.hyperdesign.delivaryUser.feature.cancelorder.ui.viewmodel

import android.net.Uri
import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState

sealed interface CancelOrderContract {
    sealed interface Action : CancelOrderContract {
        data object OnCancelOrderClicked : Action
        data object OnBackClicked : Action
        data object OpenDialog : Action
        data object CloseDialog : Action
        data class OnCommentChanged(val comment: String) : Action
        sealed interface ImageSelectionAction : Action {
            data class SelectFile(val imageFile: File, val imageUri: Uri) : ImageSelectionAction
        }
    }

    data class State(
        val comment: String = "",
        val image: TextFieldState = TextFieldState(),
        val imageFile: File? = null,
        val isDialogVisible: Boolean = false,
    ) : CancelOrderContract
}