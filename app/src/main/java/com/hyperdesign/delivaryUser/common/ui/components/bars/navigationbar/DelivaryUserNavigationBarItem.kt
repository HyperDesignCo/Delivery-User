package com.hyperdesign.delivaryUser.common.ui.components.bars.navigationbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.navigation.IAuthGraph
import com.hyperdesign.delivaryUser.common.ui.navigation.IDestination
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
/**
 * first we create the DelivaryUserNavigationBarItemState so any screen that will have the navigation bar should implement it
 * */
@Composable
fun RowScope.DelivaryUserNavigationBarItem(
    destination: DelivaryUserNavigationBarItemState,
    isSelected: Boolean,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    colors: DelivaryUserNavigationBarItemColors = DelivaryUserNavigationBarItemDefaults.colors(),
    onClick: (DelivaryUserNavigationBarItemState) -> Unit,
    textStyles: DelivaryUserNavigationBarItemTextStyles = DelivaryUserNavigationBarItemDefaults.labelStyle(),
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .height(DelivaryUserNavigationBarItemDefaults.minHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DelivaryUserNavigationIcon(
            destination = destination,
            isSelected = isSelected,
            colors = colors,
            interactionSource = interactionSource,
            onClick = { onClick(destination) }
        )
        Text(
            text = stringResource(id = destination.labelRes),
            style = textStyles.labelTextStyle(isSelected),
            color = colors.labelColor(isSelected),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

interface DelivaryUserNavigationBarItemState {
    val labelRes: Int
        @StringRes get

    val icon: Int
        @DrawableRes get

    val route: IDestination
}

@Composable
fun DelivaryUserNavigationIcon(
    destination: DelivaryUserNavigationBarItemState,
    isSelected: Boolean,
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit,
    colors: DelivaryUserNavigationBarItemColors = DelivaryUserNavigationBarItemDefaults.colors(), ) {
    Box(
        modifier = Modifier
            .size(DelivaryUserNavigationBarItemDefaults.containerSize)
            .clip(DelivaryUserNavigationBarItemDefaults.iconShape)
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple(color = colors.iconColor(isSelected)),
            )
            .background(color = colors.indicatorColor(isSelected)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = destination.icon),
            contentDescription = null,
            tint = colors.iconColor(isSelected)
        )
    }
}

data class DelivaryUserNavigationBarItemColors(
    val selectedIconColor: Color = Color.Unspecified,
    val selectedLabelColor: Color = Color.Unspecified,
    val indicatorColor: Color = Color.Unspecified,
    val unselectedIconColor: Color = Color.Unspecified,
    val unselectedLabelColor: Color = Color.Unspecified,
) {
    fun iconColor(isSelected: Boolean): Color = if (isSelected) selectedIconColor else unselectedIconColor
    fun labelColor(isSelected: Boolean): Color = if (isSelected) selectedLabelColor else unselectedLabelColor
    fun indicatorColor(isSelected: Boolean): Color = if (isSelected) indicatorColor else Color.Transparent
}

data class DelivaryUserNavigationBarItemTextStyles(
    val selectedLabelTextStyle: TextStyle = TextStyle.Default,
    val unselectedLabelTextStyle: TextStyle = TextStyle.Default,
) {
    fun labelTextStyle(isSelected: Boolean): TextStyle =
        if (isSelected) selectedLabelTextStyle else unselectedLabelTextStyle
}

object DelivaryUserNavigationBarItemDefaults {
    val minHeight: Dp = 60.dp
    val iconShape: Shape = CircleShape
    val iconSize: Dp = 32.dp
    val containerSize: Dp = 24.dp

    @Composable
    fun colors(
        selectedIconColor: Color = DelivaryUserTheme.colors.primary,
        selectedLabelColor: Color = DelivaryUserTheme.colors.secondary,
        indicatorColor: Color = DelivaryUserTheme.colors.primary.copy(0.2f),
        unselectedIconColor: Color = DelivaryUserTheme.colors.secondary,
        unselectedLabelColor: Color = DelivaryUserTheme.colors.secondary,
    ) = DelivaryUserNavigationBarItemColors(
        selectedIconColor = selectedIconColor,
        selectedLabelColor = selectedLabelColor,
        indicatorColor = indicatorColor,
        unselectedIconColor = unselectedIconColor,
        unselectedLabelColor = unselectedLabelColor,
    )

    @Composable
    fun labelStyle(
        selectedLabelTextStyle: TextStyle = DelivaryUserTheme.typography.label.small.copy(fontWeight = FontWeight.Bold),
        unselectedLabelTextStyle: TextStyle = DelivaryUserTheme.typography.label.small,
    ) = DelivaryUserNavigationBarItemTextStyles(
        selectedLabelTextStyle = selectedLabelTextStyle,
        unselectedLabelTextStyle = unselectedLabelTextStyle,
    )
}

data class PreviewNavigationItem(
    override val labelRes: Int = R.string.home,
    override val icon: Int = R.drawable.ic_home,
    override val route: IDestination = IAuthGraph.Login,
) : DelivaryUserNavigationBarItemState

@Composable
@Preview
fun DelivaryUserNavigationIconPREVIEW() = DelivaryUserTheme {
    val item = PreviewNavigationItem()
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = DelivaryUserTheme.colors.background.surfaceHigh)) {
        DelivaryUserNavigationBarItem(
            destination = item,
            isSelected = false,
            onClick = {},
            interactionSource = MutableInteractionSource()
        )
        DelivaryUserNavigationBarItem(
            destination = item,
            isSelected = false,
            onClick = {},
            interactionSource = MutableInteractionSource()
        )
        DelivaryUserNavigationBarItem(
            destination = item,
            isSelected = false,
            onClick = {},
            interactionSource = MutableInteractionSource()
        )
        DelivaryUserNavigationBarItem(
            destination = item,
            isSelected = true,
            onClick = {},
            interactionSource = MutableInteractionSource()
        )
    }

}
