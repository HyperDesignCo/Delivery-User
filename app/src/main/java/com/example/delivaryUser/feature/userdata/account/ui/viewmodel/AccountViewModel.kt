package com.example.delivaryUser.feature.userdata.account.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.delivaryUser.common.ui.navigation.IAccountGraph
import com.example.delivaryUser.common.ui.navigation.IAuthGraph
import com.example.delivaryUser.common.ui.navigation.IMainGraph
import com.example.delivaryUser.common.ui.navigation.IOrderGraph
import com.example.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.example.delivaryUser.feature.userdata.account.domain.interactors.LogOutUseCase
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.example.delivaryUser.service.user.domain.interactors.GetUserUseCase
import com.example.delivaryUser.service.user.domain.model.User
import kotlinx.coroutines.launch

class AccountViewModel(private val user: GetUserUseCase, private val logout: LogOutUseCase) :
    BaseViewModel<AccountScreenContract.State, AccountScreenContract.Action>(AccountScreenContract.State()) {
    override fun onActionTrigger(action: AccountScreenContract.Action) {
        when (action) {
            is AccountScreenContract.Action.OnAccountInfoClicked -> onAccountInfoClicked()
            is AccountScreenContract.Action.OnEditProfileClicked -> onEditClicked()
            is AccountScreenContract.Action.OnGetHelpClicked -> {}
            is AccountScreenContract.Action.OnLanguageClicked -> onLanguageClicked()
            is AccountScreenContract.Action.OnLogoutClicked -> onLogOutClicked()
            is AccountScreenContract.Action.OnMyOrdersClicked -> onMyOrdersClicked()
            is AccountScreenContract.Action.OnSavedLocationClicked -> onSavedLocationClicked()
            is AccountScreenContract.Action.Init -> getUser()
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            user.invoke(body = Unit).collectResource(onSuccess = { onGetUserSuccess(it) })
        }
    }

    private fun onGetUserSuccess(user: User) {
        updateState { copy(name = user.name, image = user.image, id = user.id) }
    }

    private fun onEditClicked() {
        fireNavigate(IAccountGraph.EditAccount)
    }

    private fun onAccountInfoClicked() {
        fireNavigate(destination = IAccountGraph.AccountInfo)
    }

    private fun onLogOutClicked() {
        viewModelScope.launch {
            logout.invoke(body = Unit).collectResource(onSuccess = { onLogOutSuccess() })
        }
    }

    private fun onLogOutSuccess() {
        fireNavigate(destination = IAuthGraph.Login, {
            popUpTo(0) {
                inclusive = true
            }
            launchSingleTop = true
        })
    }

    private fun onMyOrdersClicked() {
        fireNavigate(destination = IMainGraph.Orders)
    }

    private fun onSavedLocationClicked() {
        fireNavigate(destination = IOrderGraph.AddressList(addressType = AddressType.SENDER))
    }

    private fun onLanguageClicked() {
        viewModelScope.launch { fireNavigate(IAccountGraph.Language) }
    }
}