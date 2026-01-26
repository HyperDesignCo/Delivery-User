package com.example.delivaryUser.feature.address.mapview.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun LocationIsNotReadyYet() {
    Box(
        modifier = Modifier.fillMaxSize().background(DelivaryUserTheme.colors.background.surfaceHigh),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = DelivaryUserTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(R.string.your_location_is_being_determined),
                style = DelivaryUserTheme.typography.title.extraLarge,
                color = DelivaryUserTheme.colors.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.please_wait),
                style = DelivaryUserTheme.typography.body.small,
                color = DelivaryUserTheme.colors.secondary
            )
        }
    }
}

@Composable
@PreviewAllVariants
private fun MapWaitingLocationPreview() = DelivaryUserTheme {
    LocationIsNotReadyYet()
}
