package com.example.delivaryUser.feature.chatwithdelivery.data.mapper

import com.example.delivaryUser.common.data.mapper.Mapper
import com.example.delivaryUser.common.data.mapper.orEmpty
import com.example.delivaryUser.feature.chatwithdelivery.data.model.dto.UserChatDto
import com.example.delivaryUser.feature.chatwithdelivery.domain.model.UserChat

object UserChatMapper : Mapper<UserChatDto, UserChat, Unit>() {

    override fun dtoToDomain(model: UserChatDto): UserChat =
        UserChat(
            message = model.message.orEmpty(),
            chatMessages = ChatMessageMapper.dtoToDomain(model.chatMessages),
            chatId = model.chatId.orEmpty()
        )
}