package com.example.delivaryUser.service.location.domain.repository

import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.example.delivaryUser.service.location.domain.model.CheckLocation

interface ILocationRepository {

    suspend fun checkLocation(request: CheckLocationRequest): CheckLocation
}