package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import com.google.android.gms.maps.model.LatLng
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase

class ValidateLocationUseCase : BaseUseCase<Resource<Boolean>, LatLng>() {
    override suspend fun invoke(body: LatLng): Resource<Boolean> =
        nonFlowExecute {
            body.latitude != 0.0 || body.longitude != 0.0
        }
}
