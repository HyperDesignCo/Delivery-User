package com.hyperdesign.delivaryUser.common.di

import com.hyperdesign.delivaryUser.feature.address.mapview.di.mapModule
import com.hyperdesign.delivaryUser.feature.address.saveaddress.di.saveAddressModule
import com.hyperdesign.delivaryUser.feature.authentication.base.di.authenticationModule
import com.hyperdesign.delivaryUser.feature.authentication.changepassword.di.changePasswordModule
import com.hyperdesign.delivaryUser.feature.authentication.forgetpassword.di.forgetPasswordModule
import com.hyperdesign.delivaryUser.feature.authentication.login.di.loginModule
import com.hyperdesign.delivaryUser.feature.authentication.register.di.registerModule
import com.hyperdesign.delivaryUser.feature.authentication.splash.di.splashModule
import com.hyperdesign.delivaryUser.feature.authentication.verifyOtp.di.verifyOtpModule
import com.hyperdesign.delivaryUser.feature.cancelorder.di.cancelOrderModule
import com.hyperdesign.delivaryUser.feature.chatwithai.di.chatWithAiModule
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.di.chatWithDeliveryModule
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.di.deliveryOutZoneModule
import com.hyperdesign.delivaryUser.feature.fastorder.di.fastOrderModule
import com.hyperdesign.delivaryUser.feature.contact.di.getContactsModule
import com.hyperdesign.delivaryUser.feature.home.di.homeModule
import com.hyperdesign.delivaryUser.feature.orders.base.di.ordersModule
import com.hyperdesign.delivaryUser.feature.orders.orderdetails.di.orderDetailsModule
import com.hyperdesign.delivaryUser.feature.orders.orderslist.di.ordersListModule
import com.hyperdesign.delivaryUser.feature.pointtopoint.di.pointToPointModule
import com.hyperdesign.delivaryUser.feature.trackorder.di.trackOrderModule
import com.hyperdesign.delivaryUser.feature.userdata.account.di.accountModule
import com.hyperdesign.delivaryUser.feature.userdata.accountinfo.di.accountInfoModule
import com.hyperdesign.delivaryUser.feature.userdata.editaccount.di.editAccountModule
import com.hyperdesign.delivaryUser.feature.userdata.selectlanguage.di.selectLanguageModule
import com.hyperdesign.delivaryUser.service.address.di.addressModule
import com.hyperdesign.delivaryUser.service.language.di.languageModule
import com.hyperdesign.delivaryUser.service.location.di.locationModule
import com.hyperdesign.delivaryUser.service.user.di.userModule
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
        selectLanguageModule,
        splashModule,
        mapModule,
        locationModule,
        deliveryOutZoneModule,
        saveAddressModule,
        fastOrderModule,
        chatWithAiModule,
        cancelOrderModule,
        trackOrderModule,
        chatWithDeliveryModule,
        getContactsModule
    )
}