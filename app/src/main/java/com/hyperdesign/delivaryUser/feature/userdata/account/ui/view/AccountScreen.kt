package com.hyperdesign.delivaryUser.feature.userdata.account.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.extension.autoMirror
import com.hyperdesign.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.userdata.account.ui.viewmodel.AccountScreenContract
import com.hyperdesign.delivaryUser.feature.userdata.account.ui.viewmodel.AccountViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(viewModel: AccountViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(action = AccountScreenContract.Action.Init)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    AccountContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun AccountContent(state: AccountScreenContract.State, action: (AccountScreenContract.Action) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
    ) {
        Spacer(
            modifier = Modifier
                .height(104.dp)
                .fillMaxWidth()
                .background(color = DelivaryUserTheme.colors.primary)
        )
        Column(
            modifier = Modifier
                .padding(top = 42.dp)
                .fillMaxWidth()

        ) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                AccountMainData(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    image = state.image,
                    name = state.name
                )
                AccountMainOptionsItem(
                    modifier = Modifier.padding(top = 31.dp),
                    image = ImageVector.vectorResource(R.drawable.ic_edit_account),
                    label = stringResource(R.string.edit_account),
                    onClick = { action(AccountScreenContract.Action.OnEditProfileClicked) }
                )
                AccountMainOptionsItem(
                    modifier = Modifier.padding(top = 18.dp),
                    image = ImageVector.vectorResource(R.drawable.ic_order),
                    label = stringResource(R.string.my_orders),
                    onClick = { action(AccountScreenContract.Action.OnMyOrdersClicked) }
                )
                AccountMainOptionsItem(
                    modifier = Modifier.padding(top = 18.dp),
                    image = ImageVector.vectorResource(R.drawable.ic_help_center),
                    label = stringResource(R.string.get_help),
                    onClick = { action(AccountScreenContract.Action.OnGetHelpClicked) }
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                thickness = 4.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.07f)
            )
            AccountOptionsWithNavigation(
                modifier = Modifier.padding(horizontal = 16.dp),
                label = stringResource(R.string.account_info),
                onClick = { action(AccountScreenContract.Action.OnAccountInfoClicked) }
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                thickness = 1.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
            )
            AccountOptionsWithNavigation(
                modifier = Modifier.padding(horizontal = 16.dp),
                label = stringResource(R.string.saved_location),
                onClick = { action(AccountScreenContract.Action.OnSavedLocationClicked) }
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 16.dp),
                thickness = 1.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.1f)
            )
            AccountOptionsWithNavigation(
                modifier = Modifier.padding(horizontal = 16.dp),
                label = stringResource(
                    R.string.language
                ),
                onClick = { action(AccountScreenContract.Action.OnLanguageClicked) }
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                thickness = 4.dp,
                color = DelivaryUserTheme.colors.background.hint.copy(0.07f)
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickableWithNoRipple { action(AccountScreenContract.Action.OnLogoutClicked) },
                text = stringResource(R.string.logout),
                color = DelivaryUserTheme.colors.secondary,
                style = DelivaryUserTheme.typography.headline.extraSmall
            )
        }
    }
}

@Composable
private fun AccountMainData(
    image: String,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(90.dp)
                .clip(shape = CircleShape)
                .background(
                    shape = CircleShape, color = Color.Transparent
                ),
            model = image.takeIf { it.isNotBlank() },
            contentDescription = null,
            error = painterResource(R.drawable.img_default_user_account),
            placeholder = painterResource(R.drawable.img_default_user_account),
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier,
            text = name, color = DelivaryUserTheme.colors.secondary, style = DelivaryUserTheme.typography.headline.large
        )
    }
}

@Composable
private fun AccountMainOptionsItem(
    image: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = image,
            tint = DelivaryUserTheme.colors.secondary,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.clickableWithNoRipple { onClick() },
            text = label,
            color = DelivaryUserTheme.colors.secondary,
            style = DelivaryUserTheme.typography.body.medium
        )
    }
}

@Composable
private fun AccountOptionsWithNavigation(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = DelivaryUserTheme.colors.secondary,
            style = DelivaryUserTheme.typography.body.medium
        )
        Icon(
            modifier = Modifier
                .size(24.dp)
                .autoMirror()
                .clickableWithNoRipple { onClick() },
            imageVector = ImageVector.vectorResource(R.drawable.ic_next),
            tint = DelivaryUserTheme.colors.secondary,
            contentDescription = null,
        )
    }
}

@Composable
@PreviewAllVariants
private fun AccountScreenPreview() = DelivaryUserTheme {
    AccountContent(state = AccountScreenContract.State(), action = {})
}