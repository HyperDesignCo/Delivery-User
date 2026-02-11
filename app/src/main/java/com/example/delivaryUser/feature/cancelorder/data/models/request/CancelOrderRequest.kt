package com.example.delivaryUser.feature.cancelorder.data.models.request

import com.example.delivaryUser.common.domain.remote.IFile

data class CancelOrderRequest(val orderId: Int, val cancelReason: String, val cancelImage: IFile? = null) {
    val remoteRequest = hashMapOf<String, Any>().apply {
        put("order_id", orderId)
        put("cancel_reason", cancelReason)
    }
    val remoteRequestWithFiles = mutableListOf<Pair<String, IFile>>().apply {
        cancelImage?.let { add("cancel_reason" to it) }
    }
}