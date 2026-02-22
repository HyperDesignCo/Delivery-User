package com.example.delivaryUser.feature.contact.data.repository

import com.example.delivaryUser.feature.contact.data.mappers.ContactMapper
import com.example.delivaryUser.feature.contact.data.models.dto.ContactDto
import com.example.delivaryUser.feature.contact.domain.models.Contact
import com.example.delivaryUser.feature.contact.domain.repository.IContactRepository
import com.example.delivaryUser.feature.contact.domain.repository.remote.IContactRemoteDataSource

class ContactRepository(private val remote: IContactRemoteDataSource) : IContactRepository {
    override suspend fun getContact(): Contact =
        ContactMapper.dtoToDomain(remote.getContact().contacts ?: ContactDto())
}