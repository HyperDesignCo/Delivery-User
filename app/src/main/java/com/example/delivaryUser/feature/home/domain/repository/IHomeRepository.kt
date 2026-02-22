package com.example.delivaryUser.feature.home.domain.repository

import com.example.delivaryUser.feature.home.domain.models.HomeData

interface IHomeRepository {
    suspend fun getHomeData(): HomeData
}