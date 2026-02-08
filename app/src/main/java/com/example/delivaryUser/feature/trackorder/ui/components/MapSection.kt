package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun MapSection(
    onMapClicked: () -> Unit, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(DelivaryUserTheme.colors.background.surface)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_map_mark),
            contentDescription = "Map",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )

        Button(
            onClick = onMapClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = DelivaryUserTheme.colors.status.redAccent
            )
        ) {
            Text(
                text = "open map",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DelivaryUserTheme.colors.primary
            )
        }
    }
}