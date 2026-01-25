package com.example.delivaryUser.feature.address.mapview.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class GetSavedLocationUseCase(
    private val repository: IMapRepository
) : BaseUseCase<Flow<Resource<LatLng?>>, Unit>() {

    override suspend fun invoke(body: Unit): Flow<Resource<LatLng?>> = flowExecute {
        repository.getSavedLocation()
    }
}