package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.delivaryUser.feature.address.mapview.ui.view.MapScreen
import com.example.delivaryUser.feature.address.saveaddress.ui.view.SaveAddressScreen
import com.example.delivaryUser.feature.pointtopoint.ui.components.AddressType
import kotlinx.serialization.Serializable

sealed interface IAddressGraph {
    @Serializable
    data object AddressGraph : IGraph

    @Serializable
    data object Map : IDestination

    @Serializable
    data class SaveAddress(val addressType: AddressType) : IDestination
}

fun NavGraphBuilder.buildNavAddressGraph() {
    navigation<IAddressGraph.AddressGraph>(startDestination = IAddressGraph.Map) {
        composable<IAddressGraph.Map> { MapScreen() }
        composable<IAddressGraph.SaveAddress> { SaveAddressScreen() }
    }
}