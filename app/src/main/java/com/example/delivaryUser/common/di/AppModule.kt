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
import com.example.delivaryUser.common.ui.eventcontroller.EventController
import com.example.delivaryUser.common.ui.eventcontroller.IEventController
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.IAddressGraph
import com.example.delivaryUser.common.ui.navigation.IAuthGraph
import com.example.delivaryUser.common.ui.navigation.INavigator
import com.example.delivaryUser.common.ui.navigation.Navigator
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single<INavigator> { Navigator(startGraph = IAuthGraph.AuthGraph) }
    single<IEventController<IMessageEvent>>(qualifier = named("MessageEvent")) { EventController() }
    single<IEventController<ILoadingEvent>>(qualifier = named("LoadingEvent")) { EventController() }
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