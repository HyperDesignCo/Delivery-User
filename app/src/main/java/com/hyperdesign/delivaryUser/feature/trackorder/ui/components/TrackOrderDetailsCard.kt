package com.hyperdesign.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun OrderDetailsSheetContent(
    deliveryName: String,
    deliveryImg: String,
    deliveryFee: String,
    totalPrice: String,
    orderId: String,
    priceOfUser:String,
    orderPrice:String,
    onCallClicked: () -> Unit,
    onChatClicked: () -> Unit,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        DriverHeader(
            driverName = deliveryName,
            driverImage = deliveryImg,
            onCallClicked = onCallClicked,
            onChatClicked = onChatClicked,
            modifier = Modifier.padding(16.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp),
            color = DelivaryUserTheme.colors.background.disable,
            thickness = 6.dp
        )
        OrderSummary(
            deliveryFee = deliveryFee,
            totalPrice = totalPrice,
            orderId = orderId,
            onCancelClick = onCancelClick,
            orderPrice = orderPrice,
            priceOfUser = priceOfUser
        )
    }
}

@Composable
private fun DriverHeader(
    driverName: String,
    driverImage: String,
    onCallClicked: () -> Unit,
    onChatClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = driverImage.ifEmpty { R.drawable.img_default_user_account },
                contentDescription = "Driver Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_default_user_account),
            )
            Column(modifier = Modifier.padding(start = 13.dp)) {
                Text(
                    text = driverName,
                    style = DelivaryUserTheme.typography.title.large,
                    color = DelivaryUserTheme.colors.secondary,
                )
                Text(
                    text = stringResource(R.string.is_your_delivery_hero_for_today),
                    style = DelivaryUserTheme.typography.label.large,
                    color = DelivaryUserTheme.colors.secondary,
                )
            }
        }
        IconButton(
            onClick = onChatClicked,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(32.dp),
            interactionSource = remember { MutableInteractionSource() }) {
            Image(
                painter = painterResource(R.drawable.img_chat),
                contentDescription = "Chat",
            )
        }
        IconButton(
            onClick = onCallClicked,
            modifier = Modifier.size(32.dp),
            interactionSource = remember { MutableInteractionSource() }) {
            Image(
                painter = painterResource(R.drawable.img_phone),
                contentDescription = "Call",
            )
        }
    }
}

@Composable
private fun OrderSummary(
    deliveryFee: String,
    totalPrice: String,
    priceOfUser:String,
    orderPrice:String,
    orderId: String,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .fillMaxWidth()
    ) {

        DetailRow(
            isBold = true,
            label = stringResource(R.string.order_number), value = orderId
        )
        DetailRow(
            label = stringResource(R.string.price_of_user), value = priceOfUser
        )

        DetailRow(
            label = stringResource(R.string.price_order), value = orderPrice
        )

        DetailRow(
            label = stringResource(R.string.delivery_fee), value = deliveryFee
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = DelivaryUserTheme.colors.background.disable,
            thickness = 2.dp
        )

        DetailRow(label = stringResource(R.string.total_price), value = totalPrice, isBold = true)

        Text(
            text = stringResource(R.string.cancel_order),
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.status.redAccent,
            modifier = Modifier.clickable(onClick = { onCancelClick() })
        )
    }
}

@Composable
private fun DetailRow(
    label: String, value: String, isBold: Boolean = false, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isBold) DelivaryUserTheme.typography.title.large else DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = value,
            style = if (isBold) DelivaryUserTheme.typography.title.large else DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@PreviewAllVariants
@Composable
private fun OrderDetailsSheetContentPreview() = DelivaryUserTheme {
    OrderDetailsSheetContent(
        deliveryName = "Abdallah Alsayed",
        deliveryImg = "",
        deliveryFee = "233",
        totalPrice = "2332",
        orderId = "#123456789",
        priceOfUser = "233",
        orderPrice = "22",
        onCallClicked = {},
        onCancelClick = {},
        onChatClicked = {})
}