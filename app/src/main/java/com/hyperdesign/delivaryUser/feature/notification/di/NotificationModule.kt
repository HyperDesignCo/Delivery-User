package com.hyperdesign.delivaryUser.feature.notification.di

import com.hyperdesign.delivaryUser.feature.notification.data.repository.NotificationRepository
import com.hyperdesign.delivaryUser.feature.notification.data.repository.remote.NotificationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.notification.domain.interactors.GetNotifications
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.INotificationRepository
import com.hyperdesign.delivaryUser.feature.notification.domain.repository.remote.INotificationRemoteDataSource
import com.hyperdesign.delivaryUser.feature.notification.ui.viewmodel.NotificationViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val notificationModule = module {
    singleOf(::NotificationRemoteDataSource) bind INotificationRemoteDataSource::class
    singleOf(::NotificationRepository) bind INotificationRepository::class
    factoryOf(::GetNotifications)
    viewModelOf(::NotificationViewModel)
}