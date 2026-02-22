package com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.mapper

import com.hyperdesign.delivaryUser.common.data.mapper.Mapper
import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.data.model.dto.ChatMessageDto
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.domain.model.ChatMessage

object ChatMessageMapper: Mapper<ChatMessageDto, ChatMessage,Unit>() {

    override fun dtoToDomain(model: ChatMessageDto): ChatMessage =
        ChatMessage(
            id = model.id.orEmpty(),
            providerName = model.providerName.orEmpty(),
            deliveryName = model.deliveryName.orEmpty(),
            userName = model.userName.orEmpty(),
            message = model.message.orEmpty(),
            senderType = model.senderType.orEmpty(),
            sender = model.sender.orEmpty(),
            createAt = model.createAt.orEmpty()
        )
}