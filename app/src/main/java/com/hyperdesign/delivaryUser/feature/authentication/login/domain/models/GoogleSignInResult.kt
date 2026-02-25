package com.hyperdesign.delivaryUser.feature.authentication.login.domain.models

data class GoogleSignInResult(
    val email: String,
    val name: String,
    val socialId: String,
)