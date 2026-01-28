package com.example.delivaryUser.feature.address.mapview.di

import com.example.delivaryUser.feature.address.mapview.data.repository.MapRepository
import com.example.delivaryUser.feature.address.mapview.data.repository.local.MapLocalDataSource
import com.example.delivaryUser.feature.address.mapview.data.repository.remote.MapRemoteDataSource
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.example.delivaryUser.feature.address.mapview.domain.repository.local.IMapLocalDataSource
import com.example.delivaryUser.feature.address.mapview.domain.repository.remote.IMapRemoteDataSource
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetCurrentLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.GetSavedLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.IsFirstLaunchUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.ReverseGeocodeUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationResponseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SaveLocationUseCase
import com.example.delivaryUser.feature.address.mapview.domain.usecase.SetFirstLaunchCompleteUseCase
import com.example.delivaryUser.feature.address.mapview.ui.viewmodel.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
    factoryOf(::MapRemoteDataSource) bind IMapRemoteDataSource::class
    factoryOf(::MapLocalDataSource) bind IMapLocalDataSource::class
    factoryOf(::GetCurrentLocationUseCase)
    factoryOf(::GetSavedLocationUseCase)
    factoryOf(::IsFirstLaunchUseCase)
    factoryOf(::ReverseGeocodeUseCase)
    factoryOf(::SaveLocationUseCase)
    factoryOf(::GetLocationResponseUseCase)
    factoryOf(::SaveLocationResponseUseCase)
    factoryOf(::SetFirstLaunchCompleteUseCase)
    viewModelOf(::MapViewModel)
}