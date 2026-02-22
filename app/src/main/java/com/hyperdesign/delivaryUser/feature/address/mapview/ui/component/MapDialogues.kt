package com.hyperdesign.delivaryUser.feature.address.mapview.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoDeliveryDialog(
    onDismiss: () -> Unit,
    onPickupClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                "No Delivery Available",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Change Location", color = Color(0xFFFCB203))
            }
        },
        dismissButton = {
            TextButton(onClick = onPickupClick) {
                Text("Pickup", color = Color(0xFFF15A25))
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun DiffBranchDialog(
    onDismiss: () -> Unit,
    onChangeBranch: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Different Branch",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(onClick = onChangeBranch) {
                    Text("Yes", color = Color(0xFFF15A25))
                }
                Spacer(modifier = Modifier.size(16.dp))
                TextButton(onClick = onDismiss) {
                    Text("No", color = Color(0xFFF15A25))
                }
            }
        },
        containerColor = Color.White
    )
}