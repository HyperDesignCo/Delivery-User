package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hyperdesign.delivaryUser.feature.address.mapview.ui.component.SaveAddressDestinationType
import com.hyperdesign.delivaryUser.feature.address.mapview.ui.view.MapScreen
import com.hyperdesign.delivaryUser.feature.address.saveaddress.ui.view.SaveAddressScreen
import com.hyperdesign.delivaryUser.feature.addresslist.ui.view.AddressListScreen
import com.hyperdesign.delivaryUser.feature.fastorder.ui.view.FastOrderScreen
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.components.AddressType
import com.hyperdesign.delivaryUser.feature.pointtopoint.ui.view.PointToPointScreen
import kotlinx.serialization.Serializable

sealed interface IOrderGraph : IDestination {

    @Serializable
    data object RootGraph : IOrderGraph

    @Serializable
    data class AddressList(val id: Int? = null, val addressType: AddressType) : IOrderGraph

    @Serializable
    data object PointToPoint : IOrderGraph

    @Serializable
    data class Map(
        val addressType: AddressType = AddressType.SENDER,
        val saveAddressDestinationType: SaveAddressDestinationType = SaveAddressDestinationType.MAP_DESTINATION
    ) : IDestination

    @Serializable
    data class SaveAddress(
        val addressType: AddressType,
        val saveAddressDestinationType: SaveAddressDestinationType
        ) : IDestination

    @Serializable
    data object FastOrder : IDestination
}

fun NavGraphBuilder.buildNavOrderGraph() {
    navigation<IOrderGraph.RootGraph>(startDestination = IOrderGraph.PointToPoint) {
        composable<IOrderGraph.PointToPoint> {
            PointToPointScreen()
        }
        composable<IOrderGraph.AddressList> {
            AddressListScreen()
        }
        composable<IOrderGraph.Map> {
            MapScreen()
        }
        composable<IOrderGraph.SaveAddress> {
            SaveAddressScreen()
        }
        composable<IOrderGraph.FastOrder> {
            FastOrderScreen()
        }
    }
}