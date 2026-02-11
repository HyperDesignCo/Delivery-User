package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.trackorder.ui.viewmodel.TrackOrderContract

@Composable
fun DeliveryHeader(
    client: TrackOrderContract.Client,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(DelivaryUserTheme.colors.background.surfaceHigh)
            .padding(horizontal = 16.dp, vertical = 12.dp)

    ) {
        Text(
            text = stringResource(R.string.delivery_to),
            style = DelivaryUserTheme.typography.label.large,
            color = DelivaryUserTheme.colors.secondary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = client.image.ifEmpty { R.drawable.img_default_user_account },
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                fallback = painterResource(R.drawable.img_default_user_account)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = client.name,
                    style = DelivaryUserTheme.typography.title.medium,
                    color = DelivaryUserTheme.colors.secondary
                )

                Text(
                    text = client.address,
                    style = DelivaryUserTheme.typography.label.large,
                    color = DelivaryUserTheme.colors.secondary
                )
            }
        }
    }
}

@Composable
@PreviewAllVariants
private fun DeliveryHeaderPreview() = DelivaryUserTheme {
    DeliveryHeader(client = TrackOrderContract.Client())
}