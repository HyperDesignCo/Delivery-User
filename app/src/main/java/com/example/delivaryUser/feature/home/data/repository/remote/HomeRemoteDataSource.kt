package com.example.delivaryUser.feature.home.data.repository.remote

import com.example.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.example.delivaryUser.feature.home.data.model.dto.AdsDto
import com.example.delivaryUser.feature.home.domain.repository.remote.IHomeRemoteDataSource

class HomeRemoteDataSource(val provider: IRemoteDataSourceProvider) : IHomeRemoteDataSource {
    override suspend fun getAds(): AdsDto = provider.post(
        endpoint = ADS_ENDPOINT,
        requestBody = null,
        serializer = AdsDto.serializer(),
    )

    companion object {
        const val ADS_ENDPOINT = "ads"
    }
}