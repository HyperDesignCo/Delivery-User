package com.example.delivaryUser.service.location.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.service.location.domain.LocationResult
import com.example.delivaryUser.service.location.domain.repository.ILocationRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class ResolveLocationUseCase(
    private val repository: ILocationRepository,
) : BaseUseCase<Flow<Resource<LocationResult>>, LatLng>() {
    override suspend fun invoke(body: LatLng): Flow<Resource<LocationResult>> = flowExecute {
        repository.resolveLocation(body)
    }
}