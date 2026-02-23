package com.hyperdesign.delivaryUser.feature.notification.domain.models

data class Notification(
    val id : String,
    val userId : String ,
    val title : String ,
    val text : String
)