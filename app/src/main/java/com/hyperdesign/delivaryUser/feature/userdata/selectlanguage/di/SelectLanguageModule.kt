package com.hyperdesign.delivaryUser.feature.userdata.selectlanguage.di

import com.hyperdesign.delivaryUser.feature.userdata.selectlanguage.ui.viewmodel.SelectLanguageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val selectLanguageModule = module {
    viewModelOf(::SelectLanguageViewModel)
}