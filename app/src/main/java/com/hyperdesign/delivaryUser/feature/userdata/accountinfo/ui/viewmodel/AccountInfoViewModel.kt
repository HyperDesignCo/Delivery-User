package com.hyperdesign.delivaryUser.feature.userdata.accountinfo.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.common.ui.navigation.IAccountGraph
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.service.user.domain.interactors.GetPasswordUseCase
import com.hyperdesign.delivaryUser.service.user.domain.interactors.GetUserUseCase
import com.hyperdesign.delivaryUser.service.user.domain.model.User
import kotlinx.coroutines.launch

class AccountInfoViewModel(private val user: GetUserUseCase, private val password: GetPasswordUseCase) :
    BaseViewModel<AccountInfoContract.State, AccountInfoContract.Action>(AccountInfoContract.State()) {
    override fun onActionTrigger(action: AccountInfoContract.Action) {
        when (action) {
            is AccountInfoContract.Action.OnEditClicked -> onEditClicked()
            is AccountInfoContract.Action.Init -> updateUserData()
            is AccountInfoContract.Action.OnSaveClicked -> navigateBack()
            is AccountInfoContract.Action.OnBackClicked -> navigateBack()
        }
    }

    private fun onEditClicked() {
        fireNavigate(IAccountGraph.EditAccount)
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
            copy(name = user.name, phoneNumber = user.phone)
        }
    }

    private fun getPassword() {
        viewModelScope.launch {
            password.invoke(body = Unit).collectResource(onSuccess = { updateState { copy(password = it) } })
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            fireNavigateUp()
        }
    }
}