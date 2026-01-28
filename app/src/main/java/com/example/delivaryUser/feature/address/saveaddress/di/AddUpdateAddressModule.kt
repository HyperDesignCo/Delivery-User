package com.example.delivaryUser.feature.address.saveaddress.di

import com.example.delivaryUser.feature.address.saveaddress.data.repository.SaveAddressRepository
import com.example.delivaryUser.feature.address.saveaddress.data.repository.remote.SaveAddressDataSource
import com.example.delivaryUser.feature.address.saveaddress.domain.interactors.AddAddressUseCase
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.ISaveAddressRepository
import com.example.delivaryUser.feature.address.saveaddress.domain.repository.remote.ISaveAddressDataSource
import com.example.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addUpdateAddressModule = module {
    singleOf(::SaveAddressDataSource) bind ISaveAddressDataSource::class
    singleOf(::SaveAddressRepository) bind ISaveAddressRepository::class
    factoryOf(::AddAddressUseCase)
    viewModelOf(::SaveAddressViewModel)
}