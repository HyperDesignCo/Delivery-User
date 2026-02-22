package com.hyperdesign.delivaryUser.common.ui.components.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DeliveryUserEmptyScreen(@DrawableRes icon: Int, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(width = 144.dp, height = 128.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = text,
            style = DelivaryUserTheme.typography.headline.extraSmall,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
@PreviewAllVariants
private fun DeliveryUserEmptyScreenPreview() = DelivaryUserTheme {
    DeliveryUserEmptyScreen(
        icon = R.drawable.ic_empty_orders,
        text = stringResource(R.string.order_list_empty)
    )
}