package com.example.delivaryUser.feature.userdata.editaccount.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.data.repository.remote.ErrorKeyEnum
import com.example.delivaryUser.common.data.repository.remote.File
import com.example.delivaryUser.common.domain.exceptions.IErrorKeyEnum
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.navigation.IAccountGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.userdata.editaccount.data.model.request.EditAccountRequest
import com.example.delivaryUser.feature.userdata.editaccount.domain.interactors.EditAccountUseCase
import com.example.delivaryUser.service.user.domain.interactors.GetPasswordUseCase
import com.example.delivaryUser.service.user.domain.interactors.GetUserUseCase
import com.example.delivaryUser.service.user.domain.model.User
import kotlinx.coroutines.launch

class EditAccountViewModel(
    private val user: GetUserUseCase,
    private val password: GetPasswordUseCase,
    private val editAccount: EditAccountUseCase,
) :
    BaseViewModel<EditAccountContract.State, EditAccountContract.Action>(EditAccountContract.State()) {
    override fun onActionTrigger(action: EditAccountContract.Action) {
        when (action) {
            is EditAccountContract.Action.Init -> updateUserData()
            is EditAccountContract.Action.NameChanged -> onNameChanged(action.name)
            is EditAccountContract.Action.OnEditNameClicked -> onEditNameClicked()
            is EditAccountContract.Action.PhoneChanged -> oPhoneChanged(action.phone)
            is EditAccountContract.Action.OnEditPhoneClicked -> onEditPhoneClicked()
            is EditAccountContract.Action.OnEditPasswordClicked -> onEditPasswordClicked()
            is EditAccountContract.Action.ImageSelectionAction.SelectFile -> onFileSelected(
                imageUri = action.imageUri,
                imageFile = action.imageFile
            )
            is EditAccountContract.Action.OnSaveClicked -> onSaveClicked()
            is EditAccountContract.Action.OnBackClicked -> navigateBack()
        }
    }

    private fun updateUserData() {
        getUser()
        getPassword()
    }

    private fun getUser() {
        viewModelScope.launch {
            user.invoke(body = Unit).collectResource(::onGetUserSuccess)
        }
    }

    private fun onGetUserSuccess(user: User) {
        updateState {
            copy(
                name = name.copy(value = user.name),
                phone = phone.copy(value = user.phone),
                image = image.copy(value = user.image),
                initialName = user.name,
                initialPhone = user.phone,
                initialImage = user.image
            )
        }
    }

    private fun getPassword() {
        viewModelScope.launch {
            password.invoke(body = Unit)
                .collectResource(onSuccess = { updateState { copy(password = password.copy(value = it)) } })
        }
    }

    private fun onNameChanged(name: String) =
        updateState { copy(name = this.name.copy(value = name, error = null)) }

    private fun onEditNameClicked() {
        updateState { copy(isEditNameEnabled = true) }
    }

    private fun oPhoneChanged(phone: String) =
        updateState { copy(phone = this.phone.copy(value = phone, error = null)) }

    private fun onEditPhoneClicked() {
        updateState { copy(isEditPhoneEnabled = true) }
    }

    private fun onEditPasswordClicked() {
        viewModelScope.launch {
            fireNavigate(IAccountGraph.ChangePassword)
        }
    }

    private fun onFileSelected(imageUri: Uri, imageFile: File) {
        updateState {
            copy(
                image = image.copy(value = imageUri.toString(), error = null), imageFile = imageFile
            )
        }
    }

    private fun onSaveClicked() {
        val request = EditAccountRequest(
            name = state.value.name.value,
            phone = state.value.phone.value,
            image = state.value.imageFile
        )
        viewModelScope.launch {
            editAccount.invoke(request).collectResource(onSuccess = { navigateBack() })
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }

    override fun onRequestValidation(errors: Map<IErrorKeyEnum, UIText>) = updateState {
        copy(
            phone = phone.copy(error = errors[ErrorKeyEnum.PHONE_NUMBER]),
            password = password.copy(error = errors[ErrorKeyEnum.PASSWORD]),
            name = name.copy(error = errors[ErrorKeyEnum.NAME]),
        )
    }
}