package com.hyperdesign.delivaryUser.feature.home.domain.repository.remote

import com.hyperdesign.delivaryUser.feature.home.data.model.dto.HomeDto

interface IHomeRemoteDataSource {
    suspend fun getHomeData(): HomeDto
}