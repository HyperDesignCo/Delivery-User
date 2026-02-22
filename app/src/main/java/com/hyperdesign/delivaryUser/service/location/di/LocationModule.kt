package com.hyperdesign.delivaryUser.service.location.di

import com.hyperdesign.delivaryUser.service.location.data.repository.LocationRepository
import com.hyperdesign.delivaryUser.service.location.data.repository.local.LocationLocalDataSource
import com.hyperdesign.delivaryUser.service.location.data.repository.remote.LocationRemoteDataSource
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.hyperdesign.delivaryUser.service.location.domain.interactors.GetLocationCheckUseCase
import com.hyperdesign.delivaryUser.service.location.domain.interactors.ResolveLocationUseCase
import com.hyperdesign.delivaryUser.service.location.domain.interactors.SaveLocationCheckUseCase
import com.hyperdesign.delivaryUser.service.location.domain.repository.local.ILocationLocalDataSource
import com.hyperdesign.delivaryUser.service.location.domain.repository.remote.ILocationRemoteDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::LocationRemoteDataSource) bind ILocationRemoteDataSource::class
    singleOf(::LocationLocalDataSource) bind ILocationLocalDataSource::class
    singleOf(::LocationRepository) bind ILocationRepository::class
    factoryOf(::CheckLocationUseCase)
    factoryOf(::SaveLocationCheckUseCase)
    factoryOf(::GetLocationCheckUseCase)
    factoryOf(::ResolveLocationUseCase)
}