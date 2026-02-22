package com.hyperdesign.delivaryUser.feature.address.saveaddress.di

import com.hyperdesign.delivaryUser.feature.address.saveaddress.domain.interactors.SaveAddressUseCase
import com.hyperdesign.delivaryUser.feature.address.saveaddress.ui.viewmodel.SaveAddressViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val saveAddressModule = module {
    factoryOf(::SaveAddressUseCase)
    viewModelOf(::SaveAddressViewModel)
}