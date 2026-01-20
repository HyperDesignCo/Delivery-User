package com.example.delivaryUser.feature.home.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.home.domain.models.Ads
import com.example.delivaryUser.feature.home.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow

class GetAdsUseCase(val repository: IHomeRepository) : BaseUseCase<Flow<Resource<Ads>>, Unit>() {
    override suspend fun invoke(body: Unit): Flow<Resource<Ads>> = flowExecute {
        repository.getAds()
    }
}