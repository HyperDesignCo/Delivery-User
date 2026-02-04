package com.example.delivaryUser.common.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.navigationbar.DelivaryUserNavigationBarItemState
import com.example.delivaryUser.feature.home.ui.view.HomeScreen
import com.example.delivaryUser.feature.orders.orderdetails.ui.view.OrderDetailsScreen
import com.example.delivaryUser.feature.orders.orderslist.ui.view.OrdersScreen
import com.example.delivaryUser.feature.userdata.account.ui.view.AccountScreen
import kotlinx.serialization.Serializable

sealed interface IMainGraph : IDestination {
    @Serializable
    data object RootGraph : IGraph

    @Serializable
    data object Home : IMainGraph

    @Serializable
    data object Orders : IMainGraph

    @Serializable
    data object Chat : IMainGraph

    @Serializable
    data object Account : IMainGraph

    @Serializable
    data class OrderDetails(val id: Int) : IMainGraph
}

fun NavGraphBuilder.buildNavMainGraph() {
    navigation<IMainGraph.RootGraph>(startDestination = IMainGraph.Home) {
        composable<IMainGraph.Home> { HomeScreen() }
        composable<IMainGraph.Orders> { OrdersScreen() }
        composable<IMainGraph.OrderDetails> { OrderDetailsScreen() }
        composable<IMainGraph.Account> {  AccountScreen() }
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


    data object Chat : BottomDestination(
        labelRes = R.string.chat,
        icon = R.drawable.ic_chat,
        route = IMainGraph.Chat
    )

    data object Account : BottomDestination(
        labelRes = R.string.account,
        icon = R.drawable.ic_account,
        route = IMainGraph.Account
    )

    companion object {
        val destinations = listOf(Home, Orders, Chat, Account)
    }
}