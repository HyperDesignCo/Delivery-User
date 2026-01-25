package com.example.delivaryUser.feature.address.mapview.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun PermissionRequestScreen(onGrantClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DelivaryUserTheme.colors.background.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.location_permission_required),
                style = DelivaryUserTheme.typography.title.extraLarge,
                color = DelivaryUserTheme.colors.secondary

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.we_need_your_location_to_can_access_this_screen),
                style = DelivaryUserTheme.typography.body.large,
                color = DelivaryUserTheme.colors.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            DelivaryUserButtonPrimary(
                onClick = onGrantClick,
                label = stringResource(R.string.allow_permission),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
            )
        }
    }
}

@Composable
@PreviewAllVariants
private fun MapPermissionScreenPreview() = DelivaryUserTheme {
    PermissionRequestScreen(){

    }
}
