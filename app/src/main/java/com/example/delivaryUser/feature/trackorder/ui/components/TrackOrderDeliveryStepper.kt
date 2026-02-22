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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.orders.base.domain.models.domain.OrderStatus

data class StepItem(
    val label: String, val isCompleted: Boolean,
)

@Composable
fun DeliverySteps(
    currentStep: OrderStatus, orderState: String, time: String, modifier: Modifier = Modifier,
) {
    val steps = listOf(
        StepItem(stringResource(R.string.pending), currentStep == OrderStatus.PENDING),
        StepItem(stringResource(R.string.accepted), currentStep == OrderStatus.ACCEPTED),
        StepItem(stringResource(R.string.on_the_way), currentStep == OrderStatus.ON_WAY),
        StepItem(stringResource(R.string.delivered), currentStep == OrderStatus.DELIVERED)
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 13.dp,
                shape = RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
                clip = false
            )
            .background(
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
            ),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.estimated_time_of_arrival),
            style = DelivaryUserTheme.typography.body.medium,
            color = DelivaryUserTheme.colors.secondary,
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = time,
            style = DelivaryUserTheme.typography.title.extraLarge,
            color = DelivaryUserTheme.colors.secondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            text = orderState,
            style = DelivaryUserTheme.typography.body.medium.copy(fontSize = 13.sp),
            color = DelivaryUserTheme.colors.secondary,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 16.dp)
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
                        isCompleted = index <= currentStep.ordinal,
                        isActive = index == currentStep.ordinal
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            steps.forEachIndexed { index, step ->
                Column(
                    modifier = Modifier.weight(1f),

                    ) {
                    Text(
                        text = step.label,
                        style = DelivaryUserTheme.typography.title.small,
                        color = when {
                            index < currentStep.ordinal -> DelivaryUserTheme.colors.status.greenAccent
                            index == currentStep.ordinal -> DelivaryUserTheme.colors.secondary
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
    isCompleted: Boolean, isActive: Boolean, modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(19.dp)
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
                contentDescription = null,
                modifier = Modifier
                    .width(12.dp)
                    .height(10.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@PreviewAllVariants
@Composable
private fun DeliveryStepsPreview() = DelivaryUserTheme {
    DeliverySteps(
        currentStep = OrderStatus.ON_WAY,
        orderState = "Order is pending",
        time = "7:15 PM"
    )
}

