package com.example.delivaryUser.feature.contact.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.extension.clickableWithNoRipple
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.contact.ui.viewmodel.ContactScreenContract
import com.example.delivaryUser.feature.contact.ui.viewmodel.ContactViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(viewModel: ContactViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ContactScreenContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun ContactScreenContent(state: ContactScreenContract.State, action: (ContactScreenContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { action(ContactScreenContract.Action.OnBackClicked)},
                content = {
                    Text(
                        stringResource(R.string.get_help),
                        style = DelivaryUserTheme.typography.headline.large,
                        color = DelivaryUserTheme.colors.background.surfaceHigh
                    )
                },
            )
        },
        contentHorizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        contactField(label = stringResource(R.string.email), value = state.email)
        contactField(label = stringResource(R.string.phone_number), value = state.phone)
        Spacer(modifier = Modifier.size(48.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                16.dp,
                Alignment.CenterHorizontally
            )
        ) {
            state.socialMediaItems.forEach {
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .clickableWithNoRipple {
                            action(ContactScreenContract.Action.OnCallClicked(it.link))
                        },
                    imageVector = ImageVector.vectorResource(it.icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
private fun contactField(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = DelivaryUserTheme.typography.title.extraLarge,
            color = DelivaryUserTheme.colors.secondary
        )
        Text(
            text = value,
            style = DelivaryUserTheme.typography.title.large,
            color = DelivaryUserTheme.colors.primary
        )
    }
}

@Composable
@PreviewAllVariants
private fun ContactScreenPreview() = DelivaryUserTheme {
    ContactScreenContent(
        state = ContactScreenContract.State(
            email = "info@delivery-online.com",
            phone = "010000000000",
            socialMediaItems = listOf(
                ContactScreenContract.SocialMediaState(
                    icon = R.drawable.ic_x,
                    link = "x"
                ),
                ContactScreenContract.SocialMediaState(
                    icon = R.drawable.ic_instagram,
                    link = "instagram"
                ),
                ContactScreenContract.SocialMediaState(
                    icon = R.drawable.ic_facebook,
                    link = "facebook"
                ),
                ContactScreenContract.SocialMediaState(
                    icon = R.drawable.ic_whatsapp,
                    link = "whatsapp"
                ),
                ContactScreenContract.SocialMediaState(
                    icon = R.drawable.ic_tiktok,
                    link = "tiktok"
                ),
            )
        ), action = {})
}