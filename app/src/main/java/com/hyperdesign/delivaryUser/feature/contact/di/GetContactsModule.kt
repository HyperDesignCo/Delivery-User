package com.hyperdesign.delivaryUser.feature.contact.di

import com.hyperdesign.delivaryUser.feature.contact.data.repository.ContactRepository
import com.hyperdesign.delivaryUser.feature.contact.data.repository.remote.ContactRemoteDataSource
import com.hyperdesign.delivaryUser.feature.contact.domain.interactors.GetContactUseCase
import com.hyperdesign.delivaryUser.feature.contact.domain.repository.IContactRepository
import com.hyperdesign.delivaryUser.feature.contact.domain.repository.remote.IContactRemoteDataSource
import com.hyperdesign.delivaryUser.feature.contact.ui.viewmodel.ContactViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val getContactsModule = module {
    singleOf(::ContactRemoteDataSource) bind IContactRemoteDataSource::class
    singleOf(::ContactRepository) bind IContactRepository::class
    factoryOf(::GetContactUseCase)
    viewModelOf(::ContactViewModel)
}