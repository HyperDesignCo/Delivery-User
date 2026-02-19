package com.example.delivaryUser.feature.contact.domain.repository.remote

import com.example.delivaryUser.feature.contact.data.models.dto.ContactResponseDto

interface IContactRemoteDataSource {
    suspend fun getContact(): ContactResponseDto
}