package com.hyperdesign.delivaryUser.feature.fastorder.data.models.request

import com.hyperdesign.delivaryUser.common.data.repository.remote.File
import com.hyperdesign.delivaryUser.common.domain.remote.IFile

data class FastOrderRequest(
    val addressId: Int,
    val endAddressId: Int,
    val note: String,
    val images: List<File?>,
    val deliveryCost: Double,
    val orderType: Int = 3,
) {
    val remoteRequest = hashMapOf<String, Any>().apply {
        put("address_id", addressId)
        put("end_address_id", endAddressId)
        put("note", note)
        put("delivery_cost", deliveryCost)
        put("order_type", orderType)
    }
    val remoteRequestWithFiles = mutableListOf<Pair<String, IFile>>().apply {
        images.forEach { file ->
            add("images[]" to file as IFile)
        }
    }
}