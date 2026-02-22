package com.hyperdesign.delivaryUser.feature.contact.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.viewmodel.BaseViewModel
import com.hyperdesign.delivaryUser.feature.contact.domain.interactors.GetContactUseCase
import com.hyperdesign.delivaryUser.feature.contact.domain.models.Contact
import kotlinx.coroutines.launch

class ContactViewModel(
    private val contacts: GetContactUseCase,
) :
    BaseViewModel<ContactScreenContract.State, ContactScreenContract.Action>(ContactScreenContract.State()) {
    init {
        loadData()
    }

    override fun onActionTrigger(action: ContactScreenContract.Action) {
        when (action) {
            is ContactScreenContract.Action.OnBackClicked -> {
                viewModelScope.launch {
                    fireNavigateUp()
                }
            }

            is ContactScreenContract.Action.OnCallClicked -> {
                fireUrlEvent(action.link)
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            contacts.invoke(body = Unit).collectResource(onSuccess = ::onContactSuccess)
        }
    }

    private fun onContactSuccess(contact: Contact) {
        updateState {
            copy(
                email = contact.supportEmail, phone = contact.supportPhone,
                socialMediaItems = listOf(
                    ContactScreenContract.SocialMediaState(
                        icon = R.drawable.ic_x,
                        link = contact.x
                    ),
                    ContactScreenContract.SocialMediaState(
                        icon = R.drawable.ic_instagram,
                        link = contact.instagram
                    ),
                    ContactScreenContract.SocialMediaState(
                        icon = R.drawable.ic_facebook,
                        link = contact.facebook
                    ),
                    ContactScreenContract.SocialMediaState(
                        icon = R.drawable.ic_whatsapp,
                        link = contact.supportWhatsapp
                    ),
                    ContactScreenContract.SocialMediaState(
                        icon = R.drawable.ic_tiktok,
                        link = contact.tiktok
                    ),
                )
            )
        }
    }
}