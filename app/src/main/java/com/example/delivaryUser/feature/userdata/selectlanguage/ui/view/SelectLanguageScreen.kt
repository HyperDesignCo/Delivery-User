package com.example.delivaryUser.feature.userdata.selectlanguage.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.userdata.selectlanguage.ui.viewmodel.SelectLanguageContract
import com.example.delivaryUser.feature.userdata.selectlanguage.ui.viewmodel.SelectLanguageViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SelectLanguageScreen(viewModel: SelectLanguageViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SelectLanguageContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun SelectLanguageContent(
    state: SelectLanguageContract.State,
    action: (SelectLanguageContract.Action) -> Unit,
) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({ action(SelectLanguageContract.Action.OnBackClicked) }, content = {
                Text(
                    text = stringResource(R.string.language),
                    style = DelivaryUserTheme.typography.headline.large,
                    color = DelivaryUserTheme.colors.background.surfaceHigh
                )
            })
        },
        contentVerticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        SelectLanguageItem(
            isSelected = state.isEnglishSelected,
            icon = ImageVector.vectorResource(R.drawable.ic_english),
            label = stringResource(R.string.english),
            onClick = { action(SelectLanguageContract.Action.OnEnglishClicked) }
        )
        SelectLanguageItem(
            isSelected = state.isArabicSelected,
            icon = ImageVector.vectorResource(R.drawable.ic_arabic),
            label = stringResource(R.string.arabic),
            onClick = { action(SelectLanguageContract.Action.OnArabicClicked) })
        Spacer(modifier = Modifier.weight(1f))
        DelivaryUserButtonPrimary(
            label = stringResource(R.string.apply),
            onClick = { action(SelectLanguageContract.Action.OnApplyClicked) })
    }
}

@Composable
private fun SelectLanguageItem(
    isSelected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    val backgroundColor =
        if (isSelected) DelivaryUserTheme.colors.primary.copy(alpha = 0.29f) else DelivaryUserTheme.colors.background.surfaceHigh
    val boarderWidth = if (isSelected) 0.dp else 1.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .border(width = boarderWidth, color = DelivaryUserTheme.colors.secondary, shape = RoundedCornerShape(16.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            tint = DelivaryUserTheme.colors.primary,
            contentDescription = null
        )
        Text(
            text = label,
            style = DelivaryUserTheme.typography.headline.extraSmall,
            color = DelivaryUserTheme.colors.secondary
        )
        Spacer(modifier = Modifier.weight(1f))
        RadioButton(
            selected = isSelected,
            colors = RadioButtonDefaults.colors().copy(
                selectedColor = DelivaryUserTheme.colors.primary,
                unselectedColor = DelivaryUserTheme.colors.secondary
            ),
            onClick = { onClick() })
    }
}

@Composable
@PreviewAllVariants
private fun SelectLanguageScreenPreview() = DelivaryUserTheme {
    SelectLanguageContent(
        state = SelectLanguageContract.State(),
        action = {}
    )
}