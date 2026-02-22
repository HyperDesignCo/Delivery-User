package com.hyperdesign.delivaryUser.feature.home.domain.repository

import com.hyperdesign.delivaryUser.feature.home.domain.models.HomeData

interface IHomeRepository {
    suspend fun getHomeData(): HomeData
}