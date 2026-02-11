package com.example.delivaryUser.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun HomeTrackOrderItem(
    orderStatus: String,
    orderId: String,
    image: String,
    userName: String,
    onTrackOrderClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        HomeTrackOrderItemOrderMainData(orderStatus = orderStatus, orderId = orderId)
        HomeTrackOrderItemDeliveryMainData(
            image = image,
            userName = userName,
            onTrackOrderClicked = onTrackOrderClicked
        )
    }
}

@Composable
private fun HomeTrackOrderItemOrderMainData(
    orderStatus: String,
    orderId: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = orderStatus,
            style = DelivaryUserTheme.typography.headline.medium,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = orderId,
            style = DelivaryUserTheme.typography.body.small,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
private fun HomeTrackOrderItemDeliveryMainData(
    image: String,
    userName: String,
    onTrackOrderClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(42.dp)
                    .clip(shape = CircleShape),
                model = image,
                contentDescription = null,
                placeholder = painterResource(R.drawable.img_default_user_account),
                error = painterResource(R.drawable.img_default_user_account),
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = userName,
                style = DelivaryUserTheme.typography.headline.medium,
                color = DelivaryUserTheme.colors.secondary,
                overflow = TextOverflow.Ellipsis
            )
        }
        DelivaryUserButtonPrimary(
            modifier = Modifier
                .padding(start = 25.dp)
                .widthIn(max = 140.dp),
            label = stringResource(R.string.track_order),
            onClick = { onTrackOrderClicked() },
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
@PreviewAllVariants
fun HomeTrackOrderItemPreview() = DelivaryUserTheme {
    HomeTrackOrderItem(
        orderStatus = "Order is on the way",
        orderId = "111111111",
        image = "",
        userName = "Ahmed sayed",
        onTrackOrderClicked = {}
    )
}