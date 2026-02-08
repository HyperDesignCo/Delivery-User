package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
@Composable
fun OrderDetailsCard(
    deliveryName: String,
    deliveryImg: String,
    deliveryFee: String,
    totalPrice: String,
    orderStatus: String,
    onCallClicked: () -> Unit,
    onChatClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = DelivaryUserTheme.colors.background.surface,
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
            .padding(16.dp)
    ) {
        DriverHeader(
            driverName = deliveryName,
            driverImage = deliveryImg,
            onCallClicked = onCallClicked,
            onChatClicked = onChatClicked
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            color = DelivaryUserTheme.colors.background.disable,
            thickness = 1.dp
        )

        OrderSummary(
            deliveryFee = deliveryFee,
            totalPrice = totalPrice,
            orderStatus = orderStatus
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
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = driverImage.ifEmpty { R.drawable.img_default_user_account },
                contentDescription = "Driver",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                fallback = painterResource(R.drawable.img_default_user_account)
            )

            Text(
                text = driverName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DelivaryUserTheme.colors.primary,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row {
            IconButton(onClick = onChatClicked, modifier = Modifier.size(32.dp)) {
                Image(
                    painter =painterResource( R.drawable.img_chat),
                    contentDescription = "Chat",
                )
            }
            IconButton(onClick = onCallClicked, modifier = Modifier.size(32.dp)) {
                Image(
                    painter =painterResource(R.drawable.img_phone),
                    contentDescription = "Call",
                )
            }
        }
    }
}

@Composable
private fun OrderSummary(
    deliveryFee: String,
    totalPrice: String,
    orderStatus: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        DetailRow(
            label = stringResource(R.string.delivery),
            value = deliveryFee
        )

        DetailRow(
            label = stringResource(R.string.total),
            value = totalPrice,
            isBold = true
        )

//        if (orderStatus.isNotEmpty()) {
//            DetailRow(
//                label = stringResource(R.string.order_status),
//                value = orderStatus,
//                isBold = true
//            )
//        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    isBold: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = if (isBold) 16.sp else 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = DelivaryUserTheme.colors.primary
        )
        Text(
            text = value,
            fontSize = if (isBold) 16.sp else 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            color = DelivaryUserTheme.colors.primary
        )
    }
}

@PreviewAllVariants
@Composable
private fun OrderDetailsCardPreview() = DelivaryUserTheme {
    OrderDetailsCard(
        deliveryName = "Abdallah Alsayed",
        deliveryImg = "",
        deliveryFee = "233",
        totalPrice = "2332",
        orderStatus = "pending",
        onCallClicked = {},
        onChatClicked = {})
}
