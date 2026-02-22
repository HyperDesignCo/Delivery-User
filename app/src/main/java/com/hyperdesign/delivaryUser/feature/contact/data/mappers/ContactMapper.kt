package com.hyperdesign.delivaryUser.feature.contact.data.mappers

import com.hyperdesign.delivaryUser.common.data.mapper.orEmpty
import com.hyperdesign.delivaryUser.feature.contact.data.models.dto.ContactDto
import com.hyperdesign.delivaryUser.feature.contact.domain.models.Contact

object ContactMapper {
    fun dtoToDomain(dto: ContactDto): Contact {
        return Contact(
            id = dto.id.orEmpty(),
            supportPhone = dto.supportPhone.orEmpty(),
            supportEmail = dto.supportEmail.orEmpty(),
            supportWhatsapp = dto.supportWhatsapp.orEmpty(),
            facebook = dto.facebook.orEmpty(),
            x = dto.x.orEmpty(),
            instagram = dto.instagram.orEmpty(),
            tiktok = dto.tiktok.orEmpty()
        )
    }
}