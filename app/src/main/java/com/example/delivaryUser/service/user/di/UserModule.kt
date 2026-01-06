package com.example.delivaryUser.service.user.di

import com.example.delivaryUser.service.user.data.repository.local.UserLocalDataSource
import com.example.delivaryUser.service.user.domain.repository.local.IUserLocalDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule = module {
    singleOf(::UserLocalDataSource) bind IUserLocalDataSource::class
}