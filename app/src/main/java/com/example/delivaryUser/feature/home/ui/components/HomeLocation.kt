package com.example.delivaryUser.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun HomeLocation(
    location: String,
    onLocationClicked: () -> Unit,
    onAddLocationClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 6.dp,
                    spread = 1.dp,
                    color = DelivaryUserTheme.colors.shadow)
            )
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
            .padding(bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(3f)
                .dropShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 6.dp,
                        spread = 1.dp,
                        color = DelivaryUserTheme.colors.shadow)
                )
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh, shape = CircleShape)
                .clickableWithNoRipple {
                    onLocationClicked()
                }
                .padding(vertical = 8.dp)
                .padding(start = 24.dp, end = 50.dp)
            ,
            text = location.ifEmpty { stringResource(R.string.add_new_address) },
            style = DelivaryUserTheme.typography.body.large,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            modifier = Modifier
                .dropShadow(
                    shape = CircleShape,
                    shadow = Shadow(
                        radius = 6.dp,
                        spread = 1.dp,
                        color = DelivaryUserTheme.colors.shadow)
                )
                .background(
                    color = DelivaryUserTheme.colors.background.surfaceHigh,
                    shape = CircleShape
                )
                .clickableWithNoRipple {
                    onAddLocationClicked()
                },
            contentAlignment = Alignment.Center

        ) {
            Icon(
                modifier = Modifier
                    .padding(13.dp)
                    .size(16.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_rounded_add), contentDescription = null
            )
        }
    }
}

@PreviewAllVariants
@Composable
private fun HomeLocationPreview() = DelivaryUserTheme {
    HomeLocation(location = "22 , Street 11, El Sheikh Zayed ", onLocationClicked = {}, onAddLocationClicked = {})
}