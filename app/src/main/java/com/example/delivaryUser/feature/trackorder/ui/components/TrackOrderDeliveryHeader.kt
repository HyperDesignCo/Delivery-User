package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract

@Composable
fun TrackOrderDeliveryHeader(
    client: TrackOrderContract.Client,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DelivaryUserTheme.colors.background.surfaceHigh)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.delivery_to),
            style = DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary
        )
        Row(horizontalArrangement = Arrangement.spacedBy(13.dp)) {
            AsyncImage(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_default_user_account),
                model = client.image,
                placeholder = painterResource(R.drawable.img_default_user_account),
            )
            Column {
                Text(
                    text = client.name,
                    style = DelivaryUserTheme.typography.title.large,
                    color = DelivaryUserTheme.colors.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = client.address,
                    style = DelivaryUserTheme.typography.label.large,
                    color = DelivaryUserTheme.colors.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
@PreviewAllVariants
private fun TrackOrderDeliveryHeaderPreview() = DelivaryUserTheme {
    TrackOrderDeliveryHeader(
        client = TrackOrderContract.Client(
            name = "Youssef Mahmoud", address = "22 , Street 11, El Sheikh Zayed - El Hay 1"
        )
    )
}