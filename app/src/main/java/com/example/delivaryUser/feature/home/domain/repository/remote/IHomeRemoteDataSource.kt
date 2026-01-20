package com.example.delivaryUser.feature.home.domain.repository.remote

import com.example.delivaryUser.feature.home.data.model.dto.AdsDto

interface IHomeRemoteDataSource {
    suspend fun getAds(): AdsDto
}