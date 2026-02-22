package com.hyperdesign.delivaryUser.feature.contact.data.repository

import com.hyperdesign.delivaryUser.feature.contact.data.mappers.ContactMapper
import com.hyperdesign.delivaryUser.feature.contact.data.models.dto.ContactDto
import com.hyperdesign.delivaryUser.feature.contact.domain.models.Contact
import com.hyperdesign.delivaryUser.feature.contact.domain.repository.IContactRepository
import com.hyperdesign.delivaryUser.feature.contact.domain.repository.remote.IContactRemoteDataSource

class ContactRepository(private val remote: IContactRemoteDataSource) : IContactRepository {
    override suspend fun getContact(): Contact =
        ContactMapper.dtoToDomain(remote.getContact().contacts ?: ContactDto())
}