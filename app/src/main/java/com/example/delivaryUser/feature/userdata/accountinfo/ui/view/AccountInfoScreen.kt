package com.example.delivaryUser.feature.userdata.accountinfo.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.buttons.DelivaryUserButtonPrimary
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.userdata.accountinfo.ui.viewmodel.AccountInfoContract
import com.example.delivaryUser.feature.userdata.accountinfo.ui.viewmodel.AccountInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountInfoScreen(viewModel: AccountInfoViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(action = AccountInfoContract.Action.Init)
    }
    AccountInfoContent(state = state, viewModel::onActionTrigger)
}

@Composable
private fun AccountInfoContent(
    state: AccountInfoContract.State = AccountInfoContract.State(),
    action: (AccountInfoContract.Action) -> Unit,
) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({ action(AccountInfoContract.Action.OnBackClicked) }, content = {
                Text(
                    text = stringResource(R.string.account_info),
                    style = DelivaryUserTheme.typography.headline.large,
                    color = DelivaryUserTheme.colors.background.surfaceHigh
                )
            })
        },
        contentPadding = PaddingValues(16.dp),
        contentVerticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .clickableWithNoRipple { action(AccountInfoContract.Action.OnEditClicked) },
            text = stringResource(R.string.edit),
            style = DelivaryUserTheme.typography.body.large,
            color = DelivaryUserTheme.colors.status.darkBlueAccent
        )
        AccountInfoItem(label = stringResource(R.string.name), value = state.name)
        AccountInfoItem(label = stringResource(R.string.phone_number), value = state.phoneNumber)
        AccountInfoItem(label = stringResource(R.string.password), value = state.password)
        DelivaryUserButtonPrimary(
            modifier = Modifier.padding(top = 34.dp),
            onClick = { action(AccountInfoContract.Action.OnSaveClicked) },
            label = stringResource(R.string.save),
        )
    }
}

@Composable
private fun AccountInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp, bottom = 8.dp)
    ) {
        Text(
            modifier = Modifier,
            text = label,
            style = DelivaryUserTheme.typography.body.small,
            color = DelivaryUserTheme.colors.secondary.copy(alpha = 0.7f)
        )
        Text(
            modifier = Modifier
                .clickableWithNoRipple {},
            text = value,
            style = DelivaryUserTheme.typography.headline.extraSmall,
            color = DelivaryUserTheme.colors.secondary
        )
    }
}

@Composable
@PreviewAllVariants
private fun AccountInfoPreview() = DelivaryUserTheme {
    AccountInfoContent(state = AccountInfoContract.State(), action = {})
}