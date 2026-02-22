package com.hyperdesign.delivaryUser.feature.home.data.repository

import com.hyperdesign.delivaryUser.feature.home.data.mapper.HomeDataMapper
import com.hyperdesign.delivaryUser.feature.home.domain.models.HomeData
import com.hyperdesign.delivaryUser.feature.home.domain.repository.IHomeRepository
import com.hyperdesign.delivaryUser.feature.home.domain.repository.remote.IHomeRemoteDataSource

class HomeRepository(val remote: IHomeRemoteDataSource) : IHomeRepository {
    override suspend fun getHomeData(): HomeData = HomeDataMapper.dtoToDomain(remote.getHomeData())
}