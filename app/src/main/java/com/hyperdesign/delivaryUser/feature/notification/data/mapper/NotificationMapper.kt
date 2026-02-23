package com.hyperdesign.delivaryUser.feature.notification.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.feature.notification.data.models.dto.NotificationDto
import com.hyperdesign.delivaryUser.feature.notification.domain.models.Notification

object NotificationMapper : Mapper<NotificationDto, Notification, Unit>() {
    override fun dtoToDomain(model: NotificationDto): Notification = Notification(
        id = model.id.orEmpty(),
        userId = model.userId.orEmpty(),
        title = model.title.orEmpty(),
        text = model.text.orEmpty()
    )
}