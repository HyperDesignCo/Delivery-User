package com.hyperdesign.delivaryUser.feature.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun HomeCard(
    cardColor: Color,
    text: String,
    image: Painter,
    imageHeight: Dp,
    imageWidth: Dp,
    onCardClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(123.dp)
            .fillMaxWidth()
            .background(color = cardColor, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 28.dp)
            .clickableWithNoRipple {
                onCardClicked()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = text,
            style = DelivaryUserTheme.typography.headline.large,
            color = DelivaryUserTheme.colors.background.background
        )
        Image(
            modifier = Modifier.size(width = imageWidth, height = imageHeight),
            painter = image,
            contentDescription = null
        )
    }
}

@Composable
@PreviewAllVariants
private fun HomeCardPreview() = DelivaryUserTheme {
    Column() {
        HomeCard(
            cardColor = DelivaryUserTheme.colors.status.greenAccent,
            text = stringResource(R.string.fast_order),
            image = painterResource(R.drawable.img_fast_order),
            imageHeight = 75.dp,
            imageWidth = 98.dp,
            onCardClicked = {}
        )
        HomeCard(
            cardColor = DelivaryUserTheme.colors.status.redAccent,
            text = stringResource(R.string.order_with_ai),
            image = painterResource(R.drawable.img_order_with_ai),
            imageHeight = 71.dp,
            imageWidth = 63.dp,
            onCardClicked = {}
        )
    }

}