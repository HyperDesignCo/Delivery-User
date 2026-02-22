package com.hyperdesign.delivaryUser.feature.contact.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.contact.domain.models.Contact
import com.hyperdesign.delivaryUser.feature.contact.domain.repository.IContactRepository
import kotlinx.coroutines.flow.Flow

class GetContactUseCase(private val repository: IContactRepository) : BaseUseCase<Flow<Resource<Contact>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Contact>> =
        flowExecute { repository.getContact() }
}