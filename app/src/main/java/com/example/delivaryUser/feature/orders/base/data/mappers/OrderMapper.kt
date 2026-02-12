package com.example.delivaryUser.feature.orders.base.data.mappers

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.feature.orders.base.data.models.dto.OrderDto
import com.example.delivaryUser.feature.orders.base.domain.models.domain.Order
import com.example.delivaryUser.feature.orders.base.domain.models.domain.toOrderStatus

object OrderMapper : Mapper<OrderDto, Order, Unit>() {
    override fun dtoToDomain(model: OrderDto): Order = Order(
        id = model.id.orEmpty(),
        userId = model.userId.orEmpty(),
        userName = model.userName.orEmpty(),
        deliveryName = model.deliveryName.orEmpty(),
        deliveryImage = model.deliveryImage.orEmpty(),
        orderStatus = model.orderStatus.orEmpty().toOrderStatus(),
        note = model.note.orEmpty(),
        orderPrice = model.orderPrice.orEmpty(),
        deliveryPrice = model.deliveryPrice.orEmpty(),
        clientAddress = model.clientAddress.orEmpty(),
        endAddress = model.endAddress.orEmpty(),
        providerName = model.providerName.orEmpty(),
        clientName = model.clientName.orEmpty(),
        clientPhone = model.clientPhone.orEmpty(),
        clientLatitude = model.clientLatitude.orEmpty(),
        clientLongitude = model.clientLongitude.orEmpty(),
        endClientAddress = model.endClientAddress.orEmpty(),
        endClientLatitude = model.endClientLatitude.orEmpty(),
        endClientLongitude = model.endClientLongitude.orEmpty(),
        deliveryLatitude = model.deliveryLatitude.orEmpty(),
        deliveryLongitude = model.deliveryLongitude.orEmpty(),
        chatId = model.chatId.orEmpty(),
        cancelReason = model.cancelReason.orEmpty(),
        rates = RateMapper.dtoToDomain(model.rates.orEmpty()),
        deliveryPhone = model.deliveryPhone.orEmpty(),
        deliveryRate = model.deliveryRate.orEmpty(),
        deliveryFees = model.deliveryFees.orEmpty(),
        totalOrderPrice = model.totalOrderPrice.orEmpty(),
        createdAt = model.createdAt.orEmpty(),
        orderPurpose = model.orderPurpose.orEmpty(),
        cancelUserType = model.cancelUserType.orEmpty(),
        cancelImage = model.cancelImage.orEmpty(),
        deliveryTime = model.deliveryTime.orEmpty(),
        deliveryId = model.deliveryId.orEmpty(),
        images = OrderImageMapper.dtoToDomain(list = model.images),
        userImage = model.userImage.orEmpty(),
        orderType = model.orderType.orEmpty()
    )
}