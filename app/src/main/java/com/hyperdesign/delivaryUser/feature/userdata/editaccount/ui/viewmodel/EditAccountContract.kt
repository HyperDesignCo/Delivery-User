package com.hyperdesign.delivaryUser.feature.userdata.editaccount.ui.viewmodel

import android.net.Uri
import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.ui.filedstate.TextFieldState

sealed interface EditAccountContract {
    sealed interface Action {
        data object Init : Action
        data class NameChanged(val name: String) : Action
        data object OnEditNameClicked : Action
        data object OnEditPhoneClicked : Action
        data class PhoneChanged(val phone: String) : Action
        data object OnEditPasswordClicked : Action
        data object OnSaveClicked: Action
        data object OnBackClicked : Action
        sealed interface ImageSelectionAction : Action {
            data class SelectFile(val imageFile: File, val imageUri: Uri) : ImageSelectionAction
        }
    }

    data class State(
        val image: TextFieldState = TextFieldState(),
        val imageFile: File? = null,
        val name: TextFieldState = TextFieldState(),
        val isEditNameEnabled: Boolean = false,
        val isEditPhoneEnabled: Boolean = false,
        val isEditPasswordEnabled: Boolean = false,
        val phone: TextFieldState = TextFieldState(),
        val password: TextFieldState = TextFieldState(),
        val initialName: String = "",
        val initialPhone: String = "",
        val initialImage: String = "",
    ) {
        val isDataChanged: Boolean
            get() = name.value != initialName || phone.value != initialPhone || imageFile != null
        
        val hasErrors: Boolean
            get() = name.error != null || phone.error != null || image.error != null
            
        val isSaveEnabled: Boolean
            get() = isDataChanged && !hasErrors
    }
}