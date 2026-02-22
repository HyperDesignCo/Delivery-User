package com.hyperdesign.delivaryUser.feature.userdata.accountinfo.di

import com.hyperdesign.delivaryUser.feature.userdata.accountinfo.ui.viewmodel.AccountInfoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountInfoModule = module {
    viewModelOf(::AccountInfoViewModel)
}