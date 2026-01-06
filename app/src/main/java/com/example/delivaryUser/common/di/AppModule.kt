package com.example.delivaryUser.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.delivaryUser.common.data.repository.local.LocalDataSourceProvider
import com.example.delivaryUser.common.data.repository.remote.ApiService
import com.example.delivaryUser.common.data.repository.remote.RemoteDataSourceProvider
import com.example.delivaryUser.common.data.repository.remote.provideHttpClient
import com.example.delivaryUser.common.domain.local.ILocalDataSourceProvider
import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            encodeDefaults = true
        }
    }
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create {
            get<Context>().preferencesDataStoreFile("delivary_user_datastore")
        }
    }
    singleOf(::LocalDataSourceProvider) bind ILocalDataSourceProvider::class
    single<ApiService> { ApiService(provideHttpClient(get())) }
    singleOf(::RemoteDataSourceProvider) bind IRemoteDataSourceProvider::class
    includes(featuresModule)
}