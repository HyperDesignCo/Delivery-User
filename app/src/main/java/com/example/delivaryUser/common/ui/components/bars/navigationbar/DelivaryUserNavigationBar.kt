package com.example.delivaryUser.common.ui.components.bars.navigationbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserNavigationBar(
    navController: NavHostController,
    destinations: List<DelivaryUserNavigationBarItemState>,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = DalivaryUserNavigationBarDefaults.shape,
    containerColor: Color = DalivaryUserNavigationBarDefaults.containerColor,
    elevation: Dp = DalivaryUserNavigationBarDefaults.elevation,
    onFloatingButtonClick: () -> Unit,
    item: @Composable RowScope.(
        isSelected: Boolean,
        destination: DelivaryUserNavigationBarItemState,
        onClick: (DelivaryUserNavigationBarItemState) -> Unit,
    ) -> Unit = { isSelected, destination, onClick ->
        DelivaryUserNavigationBarItem(
            isSelected = isSelected, destination = destination, onClick = onClick,
        )
    },
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination by remember { derivedStateOf { navBackStackEntry?.destination } }
    val navigationBarState by remember {
        derivedStateOf { destinations.any { currentDestination?.hasRoute(it.route::class) == true } }
    }
    DelivaryUserNavigationBar(
        navigationBarState = navigationBarState,
        destinations = destinations,
        currentDestination = currentDestination,
        onClick = navController::navigateToBottomDestination,
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        item = item,
        onFloatingButtonClick = onFloatingButtonClick,
        elevation = elevation
    )
}

@Composable
private fun DelivaryUserNavigationBar(
    navigationBarState: Boolean,
    currentDestination: NavDestination?,
    destinations: List<DelivaryUserNavigationBarItemState>,
    onClick: (DelivaryUserNavigationBarItemState) -> Unit,
    onFloatingButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = DalivaryUserNavigationBarDefaults.shape,
    containerColor: Color = DalivaryUserNavigationBarDefaults.containerColor,
    elevation: Dp = DalivaryUserNavigationBarDefaults.elevation,
    item: @Composable RowScope.(
        isSelected: Boolean,
        destination: DelivaryUserNavigationBarItemState,
        onClick: (DelivaryUserNavigationBarItemState) -> Unit,
    ) -> Unit,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = navigationBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomAppBar(
            modifier = Modifier
                .shadow(
                    elevation = elevation,
                    clip = false
                ),
            containerColor = containerColor,
            actions = {
                destinations.fastForEach { destination ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true
                    item(isSelected, destination, onClick)
                }
            }

        )
    }
}

@Composable
private fun DelivaryUserFloatingActionButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickableWithNoRipple() {
                onClick()
            }
            .background(color = DelivaryUserTheme.colors.status.greenAccent, shape = CircleShape)) {
        Icon(
            modifier = Modifier.padding(18.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
            contentDescription = null,
            tint = DelivaryUserTheme.colors.background.surface
        )
    }
}

private fun NavHostController.navigateToBottomDestination(state: DelivaryUserNavigationBarItemState) {
    navigate(state.route) {
        popUpTo(graph.startDestinationId) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

object DalivaryUserNavigationBarDefaults {
    val shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    val elevation = 3.dp

    val containerColor @Composable get() = DelivaryUserTheme.colors.background.surfaceHigh
}

@Composable
@Preview
private fun DelivaryUserNavigationBarPreview() = DelivaryUserTheme {
    val bottomDestinations = List(3) { PreviewNavigationItem() }
    DelivaryUserNavigationBar(
        navigationBarState = true,
        currentDestination = null,
        destinations = bottomDestinations,
        onClick = {},
        onFloatingButtonClick = {},
    ) { _, destination, _ ->
        DelivaryUserNavigationBarItem(
            isSelected = false,
            destination = destination,
            onClick = {}
        )
    }
}