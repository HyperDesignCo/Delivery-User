package com.example.delivaryUser.common.di

import com.example.delivaryUser.feature.authentication.base.di.authenticationModule
import com.example.delivaryUser.feature.authentication.changepassword.di.changePasswordModule
import com.example.delivaryUser.feature.authentication.forgetpassword.di.forgetPasswordModule
import com.example.delivaryUser.feature.authentication.login.di.loginModule
import com.example.delivaryUser.feature.authentication.register.di.registerModule
import com.example.delivaryUser.feature.authentication.splash.di.splashModule
import com.example.delivaryUser.feature.authentication.verifyOtp.di.verifyOtpModule
import com.example.delivaryUser.feature.home.di.homeModule
import com.example.delivaryUser.feature.orders.base.di.ordersModule
import com.example.delivaryUser.feature.orders.orderdetails.di.orderDetailsModule
import com.example.delivaryUser.feature.orders.orderslist.di.ordersListModule
import com.example.delivaryUser.feature.pointtopoint.di.pointToPointModule
import com.example.delivaryUser.feature.userdata.account.di.accountModule
import com.example.delivaryUser.feature.userdata.accountinfo.di.accountInfoModule
import com.example.delivaryUser.feature.userdata.editaccount.di.editAccountModule
import com.example.delivaryUser.feature.userdata.selectlanguage.di.selectLanguageModule
import com.example.delivaryUser.service.address.di.addressModule
import com.example.delivaryUser.service.language.di.languageModule
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
        homeModule,
        ordersModule,
        ordersListModule,
        orderDetailsModule,
        addressModule,
        pointToPointModule,
        languageModule,
        splashModule,
        accountInfoModule,
        accountModule,
        editAccountModule,
        selectLanguageModule
    )
}