package com.example.delivaryUser.feature.home.data.repository

import com.example.delivaryUser.feature.home.data.mapper.AdsMapper
import com.example.delivaryUser.feature.home.domain.models.Ads
import com.example.delivaryUser.feature.home.domain.repository.IHomeRepository
import com.example.delivaryUser.feature.home.domain.repository.remote.IHomeRemoteDataSource

class HomeRepository(val remote: IHomeRemoteDataSource) : IHomeRepository {
    override suspend fun getAds(): Ads = AdsMapper.dtoToDomain(remote.getAds())
}