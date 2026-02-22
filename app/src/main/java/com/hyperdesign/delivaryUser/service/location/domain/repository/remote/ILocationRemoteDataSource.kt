package com.hyperdesign.delivaryUser.service.location.domain.repository.remote

import com.hyperdesign.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest

interface ILocationRemoteDataSource {
    suspend fun checkLocation(request: CheckLocationRequest): CheckLocationDto
}