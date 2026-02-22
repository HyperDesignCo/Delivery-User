package com.hyperdesign.delivaryUser.feature.contact.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.contact.data.models.dto.ContactResponseDto

interface IContactRemoteDataSource {
    suspend fun getContact(): ContactResponseDto
}