package com.hyperdesign.delivaryUser.feature.address.mapview.di

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.hyperdesign.delivaryUser.feature.address.mapview.data.repository.MapRepository
import com.hyperdesign.delivaryUser.feature.address.mapview.data.repository.local.MapLocalDataSource
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetCurrentLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.GetSavedLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.IsFirstLaunchUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.ReverseGeocodeUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.SaveLocationUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors.SetFirstLaunchCompleteUseCase
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.hyperdesign.delivaryUser.feature.address.mapview.domain.repository.local.IMapLocalDataSource
import com.hyperdesign.delivaryUser.feature.address.mapview.ui.viewmodel.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mapModule = module {
    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }
    factoryOf(::MapRepository) bind IMapRepository::class
    factoryOf(::MapLocalDataSource) bind IMapLocalDataSource::class
    factoryOf(::GetCurrentLocationUseCase)
    factoryOf(::GetSavedLocationUseCase)
    factoryOf(::IsFirstLaunchUseCase)
    factoryOf(::ReverseGeocodeUseCase)
    factoryOf(::SaveLocationUseCase)
    factoryOf(::SetFirstLaunchCompleteUseCase)
    viewModelOf(::MapViewModel)
}