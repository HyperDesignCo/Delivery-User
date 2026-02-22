package com.hyperdesign.delivaryUser.feature.orders.orderslist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun OrderRating(modifier: Modifier = Modifier, onRatingClicked: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.82f),
                shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
            .padding(vertical = 13.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.rate),
            color = DelivaryUserTheme.colors.background.surfaceHigh,
            style = DelivaryUserTheme.typography.title.large
        )
        Spacer(modifier = Modifier.weight(1f))
        for (i in 0..4) {
            val padding = if (i == 4) 0.dp else 4.dp
            Icon(
                modifier = Modifier
                    .padding(end = padding)
                    .clickableWithNoRipple {
                        onRatingClicked()
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = DelivaryUserTheme.colors.status.accentColor
            )
        }
    }
}

@Composable
@PreviewAllVariants
fun OrderRatingPreview() = DelivaryUserTheme {
    OrderRating(onRatingClicked = {})
}