package com.example.delivaryUser.service.location.domain.repository.remote

import com.example.delivaryUser.service.location.data.model.dto.CheckLocationDto
import com.example.delivaryUser.service.location.data.model.request.CheckLocationRequest

interface ILocationRemoteDataSource {
    suspend fun checkLocation(request: CheckLocationRequest): CheckLocationDto

}