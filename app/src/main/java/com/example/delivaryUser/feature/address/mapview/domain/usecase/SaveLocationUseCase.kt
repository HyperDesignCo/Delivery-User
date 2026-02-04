package com.example.delivaryUser.feature.address.mapview.domain.usecase

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class SaveLocationUseCase(
    private val repository: IMapRepository
) : BaseUseCase<Flow<Resource<Unit>>, LatLng>() {
    override suspend fun invoke(body: LatLng): Flow<Resource<Unit>> = flowExecute {
        repository.saveLocation(body)
    }
}