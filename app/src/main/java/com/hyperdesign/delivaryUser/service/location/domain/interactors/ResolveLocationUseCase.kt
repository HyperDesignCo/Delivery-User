package com.hyperdesign.delivaryUser.service.location.domain.interactors

import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.service.location.domain.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.service.location.domain.ILocationRepository
import kotlinx.coroutines.flow.Flow

class ResolveLocationUseCase(
    private val repository: ILocationRepository,
) : BaseUseCase<Flow<Resource<LocationResult>>, LatLng>() {
    override suspend fun invoke(body: LatLng): Flow<Resource<LocationResult>> = flowExecute {
        repository.resolveLocation(body)
    }
}