package com.hyperdesign.delivaryUser.service.address.di

import com.hyperdesign.delivaryUser.feature.addresslist.ui.viewmodel.AddressListViewModel
import com.hyperdesign.delivaryUser.service.address.data.repository.AddressRepository
import com.hyperdesign.delivaryUser.service.address.data.repository.local.AddressLocalDataSource
import com.hyperdesign.delivaryUser.service.address.data.repository.remote.AddressRemoteDataSource
import com.hyperdesign.delivaryUser.service.address.domain.interactors.GetAddressesUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.GetRecipientAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.GetSenderAddress
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveRecipientAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.interactors.SaveSenderAddressUseCase
import com.hyperdesign.delivaryUser.service.address.domain.repository.IAddressRepository
import com.hyperdesign.delivaryUser.service.address.domain.repository.local.IAddressLocalDataSource
import com.hyperdesign.delivaryUser.service.address.domain.repository.remote.IAddressRemoteDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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
    viewModelOf(::AddressListViewModel)
}