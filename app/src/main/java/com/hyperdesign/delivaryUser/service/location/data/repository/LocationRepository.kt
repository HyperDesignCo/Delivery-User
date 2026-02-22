package com.hyperdesign.delivaryUser.service.location.data.repository

import com.hyperdesign.delivaryUser.service.location.data.mapper.CheckLocationMapper
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import com.hyperdesign.delivaryUser.service.location.domain.repository.ILocationRepository
import com.hyperdesign.delivaryUser.service.location.domain.repository.remote.ILocationRemoteDataSource

class LocationRepository(
    private val remote: ILocationRemoteDataSource
) : ILocationRepository {
    override suspend fun checkLocation(request: CheckLocationRequest): CheckLocation =
        CheckLocationMapper.dtoToDomain(remote.checkLocation(request))
}