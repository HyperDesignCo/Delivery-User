package com.example.delivaryUser.common.di

import com.example.delivaryUser.feature.address.mapview.di.mapModule
import com.example.delivaryUser.feature.authentication.base.di.authenticationModule
import com.example.delivaryUser.feature.authentication.changepassword.di.changePasswordModule
import com.example.delivaryUser.feature.authentication.forgetpassword.di.forgetPasswordModule
import com.example.delivaryUser.feature.authentication.login.di.loginModule
import com.example.delivaryUser.feature.authentication.register.di.registerModule
import com.example.delivaryUser.feature.authentication.verifyOtp.di.verifyOtpModule
import com.example.delivaryUser.feature.home.di.homeModule
import com.example.delivaryUser.service.location.di.locationModule
import com.example.delivaryUser.service.user.di.userModule
import org.koin.dsl.module

val featuresModule = module {
    includes(
        userModule,
        authenticationModule,
        loginModule,
        registerModule,
        verifyOtpModule,
        forgetPasswordModule,
        changePasswordModule,
        mapModule,
        homeModule,
        locationModule
    )
}