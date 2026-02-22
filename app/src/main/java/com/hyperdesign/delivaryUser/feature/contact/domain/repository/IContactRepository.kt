package com.hyperdesign.delivaryUser.feature.contact.domain.repository

import com.hyperdesign.delivaryUser.feature.contact.domain.models.Contact

interface IContactRepository {
    suspend fun getContact(): Contact
}