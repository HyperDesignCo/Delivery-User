package com.hyperdesign.delivaryUser.feature.home.data.repository.remote

import com.hyperdesign.delivaryUser.common.domain.remote.IRemoteDataSourceProvider
import com.hyperdesign.delivaryUser.feature.home.data.model.dto.HomeDto
import com.hyperdesign.delivaryUser.feature.home.domain.repository.remote.IHomeRemoteDataSource

class HomeRemoteDataSource(val provider: IRemoteDataSourceProvider) : IHomeRemoteDataSource {
    override suspend fun getHomeData(): HomeDto = provider.post(
        endpoint = ADS_ENDPOINT,
        requestBody = null,
        serializer = HomeDto.serializer(),
    )

    companion object {
        const val ADS_ENDPOINT = "ads"
    }
}