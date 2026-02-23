package com.hyperdesign.delivaryUser.service.fcm

import com.google.firebase.messaging.FirebaseMessaging
import com.hyperdesign.delivaryUser.common.data.DelivaryUserException
import kotlinx.coroutines.tasks.await

object FCMManager {
    suspend fun getDeviceToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: DelivaryUserException) {
            throw e as DelivaryUserException.Client.UnAuthorized
        }
    }
}