package com.example.delivaryUser.common.ui.components.bars.navigationbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.zIndex
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
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .shadow(
                        elevation = elevation,
                        shape = ArcNavigationBarShape(
                            arcWidth = 76.dp,
                            arcHeight = 40.dp,
                            cornerRadius = 0.dp
                        ),
                        clip = false
                    ),
                color = containerColor,
                shape = ArcNavigationBarShape(
                    arcWidth = 68.dp,
                    arcHeight = 32.dp,
                    cornerRadius = 0.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    val halfSize = destinations.size / 2
                    destinations.take(halfSize).fastForEach { destination ->
                        val isSelected =
                            currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true
                        item(isSelected, destination, onClick)
                    }
                    Spacer(modifier = Modifier.width(80.dp))
                    destinations.drop(halfSize).fastForEach { destination ->
                        val isSelected =
                            currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true
                        item(isSelected, destination, onClick)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .offset(y = (-50).dp)
                    .zIndex(1f)
            ) {
                DelivaryUserFloatingActionButton(onClick = onFloatingButtonClick)
            }
        }
    }
}

@Composable
private fun DelivaryUserFloatingActionButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = false
            )
            .background(color = DelivaryUserTheme.colors.primary, shape = CircleShape)
            .clickableWithNoRipple { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(28.dp),
            painter = painterResource(id = R.drawable.img_order_with_ai),
            contentDescription = null,
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
    val elevation = 8.dp
    val containerColor @Composable get() = DelivaryUserTheme.colors.background.surfaceHigh
}

@Composable
@Preview
private fun DelivaryUserNavigationBarPreview() = DelivaryUserTheme {
    val bottomDestinations = List(4) { PreviewNavigationItem() }
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