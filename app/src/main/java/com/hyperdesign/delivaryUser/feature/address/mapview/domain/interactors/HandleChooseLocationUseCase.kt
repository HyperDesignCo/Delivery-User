package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.hyperdesign.delivaryUser.service.location.data.model.request.CheckLocationRequest
import com.hyperdesign.delivaryUser.service.location.domain.interactors.CheckLocationUseCase
import com.hyperdesign.delivaryUser.service.location.domain.interactors.SaveLocationCheckUseCase
import com.hyperdesign.delivaryUser.service.location.domain.model.CheckLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform

data class HandleChooseLocationParams(
    val targetLatLng: LatLng,
    val addressType: AddressType,
)

sealed class ChooseLocationResult {
    data class OutOfZone(val latitude: Double, val longitude: Double) : ChooseLocationResult()
    data class NavigateToSaveAddress(val addressType: AddressType) : ChooseLocationResult()
    data class CheckLocationLoaded(val checkLocation: CheckLocation) : ChooseLocationResult()
    data object Loading : ChooseLocationResult()
}

class HandleChooseLocationUseCase(
    private val checkLocationUseCase: CheckLocationUseCase,
    private val saveLocationCheckUseCase: SaveLocationCheckUseCase,
) : BaseUseCase<Flow<Resource<ChooseLocationResult>>, HandleChooseLocationParams>() {

    override suspend fun invoke(
        body: HandleChooseLocationParams,
    ): Flow<Resource<ChooseLocationResult>> = flow {
        val request = CheckLocationRequest(
            latitude = body.targetLatLng.latitude.toString(),
            longitude = body.targetLatLng.longitude.toString(),
        )

        checkLocationUseCase.invoke(request).transform { checkResource ->
            when (checkResource) {
                is Resource.Loading -> emit(Resource.Loading(checkResource.isLoading))
                is Resource.Failure -> emit(Resource.Failure(checkResource.exception))
                is Resource.Success -> {
                    val checkLocation = checkResource.model
                    emit(Resource.Success(ChooseLocationResult.CheckLocationLoaded(checkLocation)))

                    val isOutOfZone = checkLocation.data.currentRegion.isNullOrEmpty() ||
                            checkLocation.data.currentArea.isNullOrEmpty()

                    if (isOutOfZone) {
                        emit(
                            Resource.Success(
                                ChooseLocationResult.OutOfZone(
                                    latitude = body.targetLatLng.latitude,
                                    longitude = body.targetLatLng.longitude,
                                )
                            )
                        )
                    } else {
                        emitAll(
                            saveLocationCheckUseCase.invoke(checkLocation.data).map { saveResource ->
                                when (saveResource) {
                                    is Resource.Loading -> Resource.Loading(saveResource.isLoading)
                                    is Resource.Failure -> Resource.Failure(saveResource.exception)
                                    is Resource.Success -> Resource.Success(
                                        ChooseLocationResult.NavigateToSaveAddress(body.addressType)
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }.also { emitAll(it) }
    }
}
