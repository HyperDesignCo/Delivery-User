package com.example.delivaryUser.feature.trackorder.di

import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val trackOrderModule = module {

    viewModelOf(::TrackOrderViewModel)

}