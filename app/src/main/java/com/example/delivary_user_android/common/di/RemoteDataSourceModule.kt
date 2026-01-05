package com.example.delivary_user_android.common.di

import com.example.delivary_user_android.common.data.remote.ApiService
import com.example.delivary_user_android.common.data.remote.RemoteDataSourceProvider
import com.example.delivary_user_android.common.data.remote.provideHttpClient
import com.example.delivary_user_android.common.domain.remote.IRemoteDataSourceProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<ApiService> { ApiService(provideHttpClient(get())) }
    singleOf(::RemoteDataSourceProvider) bind IRemoteDataSourceProvider::class
}