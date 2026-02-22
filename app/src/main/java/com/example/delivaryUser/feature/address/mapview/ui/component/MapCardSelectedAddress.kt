package com.example.delivaryUser.feature.address.mapview.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun SelectedLocationCard(address: String?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, DelivaryUserTheme.colors.background.stroke),
        colors = CardDefaults.cardColors(containerColor = DelivaryUserTheme.colors.background.surfaceHigh)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.selected_address),
                style = DelivaryUserTheme.typography.body.medium,
                color = DelivaryUserTheme.colors.secondary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = address ?: "",
                style = DelivaryUserTheme.typography.label.large,
                color = DelivaryUserTheme.colors.secondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
@PreviewAllVariants
private fun MapCardSelectedAddressPreview() = DelivaryUserTheme {
    SelectedLocationCard(address = "Giza,Egypt")
}
