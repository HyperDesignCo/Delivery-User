package com.example.delivaryUser.feature.cancelorder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonSecondary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun CancelOrderDialog(
    onKeepClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = { onCancelClicked() }) {
        Column(
            modifier = modifier
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh,
                    shape = RoundedCornerShape(20.dp)
                )
                .size(236.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.sure_to_cancel),
                style = DelivaryUserTheme.typography.headline.extraSmall,
                color = DelivaryUserTheme.colors.secondary
            )
            Row(modifier = Modifier.padding(horizontal = 11.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                DelivaryUserButtonSecondary(
                    modifier = Modifier
                        .weight(1f),
                    label = stringResource(R.string.cancel), onClick = { onCancelClicked() })
                DelivaryUserButtonPrimary(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    label = stringResource(R.string.keep), onClick = { onKeepClicked() })
            }
        }
    }
}
@Composable
@PreviewAllVariants
private fun DelivaryUserLoadingDialogPreview() = DelivaryUserTheme {
    CancelOrderDialog({}, {})
}