package com.hyperdesign.delivaryUser.feature.address.mapview.domain.interactors

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.PlacesClient
import com.hyperdesign.delivaryUser.BuildConfig
import com.hyperdesign.delivaryUser.common.domain.Resource
import com.hyperdesign.delivaryUser.common.domain.usecase.BaseUseCase

class InitializePlacesApiUseCase(
    private val context: Context,
) : BaseUseCase<Resource<Pair<PlacesClient, AutocompleteSessionToken>>, Unit>() {

    override suspend fun invoke(body: Unit): Resource<Pair<PlacesClient, AutocompleteSessionToken>> =
        nonFlowExecute {
            if (!Places.isInitialized()) {
                Places.initialize(context, BuildConfig.MAPS_API_KEY)
            }
            val client = Places.createClient(context)
            val token = AutocompleteSessionToken.newInstance()
            Pair(client, token)
        }
}
