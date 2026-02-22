package com.hyperdesign.delivaryUser.feature.fastorder.ui.viewmodel

import android.net.Uri
import com.hyperdesign.delivaryUser.common.data.repository.remote.File

sealed interface FastOrderContract {
    sealed interface Action : FastOrderContract {
        data class OnOrderDetailsChanged(val orderDetails: String) : Action
        sealed interface ImageSelectionAction : Action {
            data class SelectFile(val imageFile: File, val imageUri: Uri) : ImageSelectionAction
        }
        data class OnImageRemoveClicked(val index : Int) : Action
        data object OnCheckOutClicked : Action
        data object Init : Action
    }

    data class State(
        val addressId : Int = 0,
        val location: String= "",
        val orderDetails: String = "",
        val images: MutableList<ImageState> = mutableListOf(),
        val deliveryCost: Double = 0.0
    )

    data class ImageState(
        val image: String = "",
        val imageFile: File? = null,
    )
}