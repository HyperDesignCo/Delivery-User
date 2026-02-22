package com.example.delivaryUser.common.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: DelivaryUserButtonColors = DelivaryUserButtonDefaults.color(),
    isEnabled: Boolean = true,
    contentPadding: PaddingValues = DelivaryUserButtonDefaults.paddingValues,
    shape: Shape = DelivaryUserButtonDefaults.shape,
    labelStyle: TextStyle = DelivaryUserButtonDefaults.labelStyle,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape),
        shape = shape,
        colors = ButtonColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        ),
        border = BorderStroke(width = DelivaryUserButtonDefaults.borderWidth, color = colors.boarderColor),
        contentPadding = contentPadding,
        enabled = isEnabled,
        onClick = onClick
    ) {
        Text(
            text = label, style = labelStyle
        )
    }
}

data class DelivaryUserButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val boarderColor: Color,
)

object DelivaryUserButtonDefaults {
    var borderWidth = 1.dp
    var shape = RoundedCornerShape(50.dp)
    val labelStyle: TextStyle
        @Composable
        get() = DelivaryUserTheme.typography.headline.small
    var paddingValues = PaddingValues(0.dp)
    fun color(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        boarderColor: Color = Color.Unspecified,
    ) =
        DelivaryUserButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContentColor,
            disabledContentColor = disabledContainerColor,
            boarderColor = boarderColor
        )
}

@Composable
@PreviewAllVariants
@Preview(showBackground = true)
private fun DelivaryUserButtonPreview() = DelivaryUserTheme {
    DelivaryUserButton(
        label = "login",
        onClick = {}
    )
}