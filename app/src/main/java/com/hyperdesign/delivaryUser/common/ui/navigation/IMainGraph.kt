package com.hyperdesign.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.bars.navigationbar.DelivaryUserNavigationBarItemState
import com.hyperdesign.delivaryUser.feature.cancelorder.ui.view.CancelOrderScreen
import com.hyperdesign.delivaryUser.feature.chatwithai.ui.view.ChatWithAiScreen
import com.hyperdesign.delivaryUser.feature.chatwithdelivery.ui.view.ChatWithDeliveryScreen
import com.hyperdesign.delivaryUser.feature.deliveryoutzone.ui.view.DeliveryOutZoneScreen
import com.hyperdesign.delivaryUser.feature.home.ui.view.HomeScreen
import com.hyperdesign.delivaryUser.feature.notification.ui.view.NotificationScreen
import com.hyperdesign.delivaryUser.feature.orders.orderdetails.ui.view.OrderDetailsScreen
import com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.view.OrdersScreen
import com.hyperdesign.delivaryUser.feature.trackorder.ui.view.TrackOrderScreen
import com.hyperdesign.delivaryUser.feature.userdata.account.ui.view.AccountScreen
import kotlinx.serialization.Serializable

sealed interface IMainGraph : IDestination {
    @Serializable
    data object RootGraph : IGraph

    @Serializable
    data object Home : IMainGraph

    @Serializable
    data object Orders : IMainGraph

    @Serializable
    data object Notifications : IMainGraph

    @Serializable
    data object Account : IMainGraph

    @Serializable
    data class OrderDetails(val id: Int) : IMainGraph

    @Serializable
    data class CancelOrder(val id: Int) : IMainGraph

    @Serializable
    data class DeliveryOutZone(val latitude: Double, val longitude: Double) : IMainGraph

    @Serializable
    data object Chat : IMainGraph

    @Serializable
    data class TrackOrder(val id: Int) : IMainGraph

    @Serializable
    data class ChatWithDelivery(
        val chatId: String,
        val orderId: String,
        val deliveryId: String,
        val deliveryImg: String,
        val deliveryName:String,
        val deliveryNumber:String,
        val isNewChat: Boolean
    ) : IMainGraph
}

fun NavGraphBuilder.buildNavMainGraph() {
    navigation<IMainGraph.RootGraph>(startDestination = IMainGraph.Home) {
        composable<IMainGraph.Home> { HomeScreen() }
        composable<IMainGraph.Orders> { OrdersScreen() }
        composable<IMainGraph.OrderDetails> { OrderDetailsScreen() }
        composable<IMainGraph.Account> { AccountScreen() }
        composable<IMainGraph.DeliveryOutZone> { DeliveryOutZoneScreen() }
        composable<IMainGraph.Notifications> { NotificationScreen()}
        composable<IMainGraph.Chat> { ChatWithAiScreen() }
        composable<IMainGraph.TrackOrder> { TrackOrderScreen() }
        composable<IMainGraph.CancelOrder> { CancelOrderScreen() }
        composable<IMainGraph.ChatWithDelivery> { ChatWithDeliveryScreen() }

    }
}

sealed class BottomDestination(
    override val labelRes: Int,
    override val icon: Int,
    override val route: IDestination,
) : DelivaryUserNavigationBarItemState {
    data object Home : BottomDestination(
        labelRes = R.string.home,
        icon = R.drawable.ic_home,
        route = IMainGraph.Home
    )

    data object Orders : BottomDestination(
        labelRes = R.string.order,
        icon = R.drawable.ic_order,
        route = IMainGraph.Orders
    )


    data object Notifications : BottomDestination(
        labelRes = R.string.notifications,
        icon = R.drawable.ic_notification,
        route = IMainGraph.Notifications
    )

    data object Account : BottomDestination(
        labelRes = R.string.account,
        icon = R.drawable.ic_account,
        route = IMainGraph.Account
    )

    companion object {
        val destinations = listOf(Home, Orders, Notifications, Account)
    }
}