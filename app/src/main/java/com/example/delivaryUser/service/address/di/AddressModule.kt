package com.example.delivaryUser.service.address.di

import com.example.delivaryUser.service.address.data.repository.AddressRepository
import com.example.delivaryUser.service.address.data.repository.local.AddressLocalDataSource
import com.example.delivaryUser.service.address.data.repository.remote.AddressRemoteDataSource
import com.example.delivaryUser.service.address.domain.interactors.GetAddressesUseCase
import com.example.delivaryUser.service.address.domain.interactors.GetRecipientAddressUseCase
import com.example.delivaryUser.service.address.domain.interactors.GetSenderAddress
import com.example.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.example.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.example.delivaryUser.service.address.domain.repository.IAddressRepository
import com.example.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
import com.example.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addressModule = module {
    singleOf(::AddressRepository) bind IAddressRepository::class
    singleOf(::AddressRemoteDataSource) bind IAddressRemoteDataSource::class
    singleOf(::AddressLocalDataSource) bind IAddressLocalDataSource::class
    factoryOf(::GetAddressesUseCase)
    factoryOf(::GetSenderAddress)
    factoryOf(::GetRecipientAddressUseCase)
    factoryOf(::SaveSenderAddressUseCase)
    factoryOf(::SaveRecipientAddressUseCase)
}