package com.example.delivaryUser.feature.home.domain.repository.remote

import com.example.delivaryUser.feature.home.data.model.dto.HomeDto

interface IHomeRemoteDataSource {
    suspend fun getHomeData(): HomeDto
}