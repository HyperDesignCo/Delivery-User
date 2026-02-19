package com.example.delivaryUser.feature.contact.domain.repository

import com.example.delivaryUser.feature.contact.domain.models.Contact

interface IContactRepository {
    suspend fun getContact(): Contact
}