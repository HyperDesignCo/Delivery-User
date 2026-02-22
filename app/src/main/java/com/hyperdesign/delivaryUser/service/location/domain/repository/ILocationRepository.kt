package com.hyperdesign.delivaryUser.service.location.domain.repository

import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation

interface ILocationRepository {
    suspend fun checkLocation(request: CheckLocationRequest): CheckLocation
}