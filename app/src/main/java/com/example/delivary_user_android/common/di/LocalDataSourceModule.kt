package com.example.delivary_user_android.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.delivary_user_android.common.data.loocal.LocalDataSourceProvider
import com.example.delivary_user_android.common.domain.local.ILocalDataSourceProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val localDataSourceModule = module {
    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create {
            get<Context>().preferencesDataStoreFile("delivary_user_datastore")
        }
    }
    singleOf(::LocalDataSourceProvider) bind ILocalDataSourceProvider::class
}