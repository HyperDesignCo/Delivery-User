package com.example.delivaryUser.feature.contact.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.contact.data.models.dto.ContactResponseDto
import com.example.delivaryUser.feature.contact.domain.repository.remote.IContactRemoteDataSource

class ContactRemoteDataSource(private val provider: IRemoteDataSourceProvider) : IContactRemoteDataSource {
    override suspend fun getContact(): ContactResponseDto = provider.get(
        endpoint = CONTACT_ENDPOINT,
        serializer = ContactResponseDto.serializer(),
    )

    companion object {
        const val CONTACT_ENDPOINT = "getcontact"
    }
}