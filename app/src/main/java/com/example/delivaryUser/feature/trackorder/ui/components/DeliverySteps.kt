package com.example.delivaryUser.feature.trackorder.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

data class StepItem(
    val label: String, val isCompleted: Boolean
)

@Composable
fun DeliverySteps(
    currentStep: Int, time: String, modifier: Modifier = Modifier
) {
    val steps = listOf(
        StepItem(stringResource(R.string.waiting_for_the_market_response), currentStep >= 0),
        StepItem(stringResource(R.string.the_order_is_now_being_prepared), currentStep >= 1),
        StepItem(stringResource(R.string.on_my_way), currentStep >= 2),
        StepItem(stringResource(R.string.it_was_received), currentStep >= 3)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = DelivaryUserTheme.colors.background.surface,
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.estimated_time_of_arrival),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = time,
            style = DelivaryUserTheme.typography.title.extraLarge,
            color = DelivaryUserTheme.colors.secondary,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = "Order from seoudisupermarket is being prepared now",
            style = DelivaryUserTheme.typography.label.medium,
            color = DelivaryUserTheme.colors.secondary,
            modifier = Modifier.padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .height(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .align(Alignment.Center)
                    .background(
                        color = DelivaryUserTheme.colors.background.disable,
                        shape = RoundedCornerShape(2.dp)
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                steps.forEachIndexed { index, _ ->
                    StepCircle(
                        isCompleted = index <= currentStep, isActive = index == currentStep
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            steps.forEachIndexed { index, step ->
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = step.label,
                        style = DelivaryUserTheme.typography.title.small,
                        color = when {
                            index < currentStep -> DelivaryUserTheme.colors.status.greenAccent
                            index == currentStep -> DelivaryUserTheme.colors.secondary
                            else -> DelivaryUserTheme.colors.secondaryVariant
                        },

                        )
                }
            }
        }
    }
}

@Composable
private fun StepCircle(
    isCompleted: Boolean, isActive: Boolean, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(
                color = when {
                    isActive -> DelivaryUserTheme.colors.status.redAccent
                    isCompleted -> DelivaryUserTheme.colors.status.greenAccent
                    else -> DelivaryUserTheme.colors.background.disable
                }
            ), contentAlignment = Alignment.Center
    ) {
        if (isCompleted || isActive) {
            Image(
                painter = painterResource(R.drawable.img_order_track_check),
                contentDescription = "Completed",
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@PreviewAllVariants
@Composable
private fun DeliveryStepsPreview() = DelivaryUserTheme {
    DeliverySteps(currentStep = 2, time = "7:15 PM")
}

