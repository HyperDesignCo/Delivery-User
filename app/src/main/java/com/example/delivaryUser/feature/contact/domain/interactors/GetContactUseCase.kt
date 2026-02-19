package com.example.delivaryUser.feature.contact.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.contact.domain.models.Contact
import com.example.delivaryUser.feature.contact.domain.repository.IContactRepository
import kotlinx.coroutines.flow.Flow

class GetContactUseCase(private val repository: IContactRepository) : BaseUseCase<Flow<Resource<Contact>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Contact>> =
        flowExecute { repository.getContact() }
}