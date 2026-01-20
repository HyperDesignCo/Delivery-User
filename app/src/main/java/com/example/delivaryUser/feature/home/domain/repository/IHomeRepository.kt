package com.example.delivaryUser.feature.home.domain.repository

import com.example.delivaryUser.feature.home.domain.models.Ads

interface IHomeRepository {
    suspend fun getAds(): Ads
}