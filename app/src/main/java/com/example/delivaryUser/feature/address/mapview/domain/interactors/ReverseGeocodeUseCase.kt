package com.example.delivaryUser.feature.address.mapview.domain.interactors

import com.example.delivaryUser.common.domain.Resource
import com.example.delivaryUser.common.domain.usecase.BaseUseCase
import com.example.delivaryUser.feature.address.mapview.domain.repository.IMapRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

class ReverseGeocodeUseCase(
    private val repository: IMapRepository
) : BaseUseCase<Flow<Resource<String>>, LatLng>() {

    override suspend fun invoke(body: LatLng): Flow<Resource<String>> = flowExecute {
        repository.reverseGeocode(body)
    }
}