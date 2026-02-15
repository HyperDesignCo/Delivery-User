package com.example.delivaryUser.feature.chatwithdelivery.ui.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.chatwithdelivery.ui.components.ChatInputField
import com.example.delivaryUser.feature.chatwithdelivery.ui.components.ChatMessageBubble
import com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryContract
import com.example.delivaryUser.feature.chatwithdelivery.ui.viewmodel.ChatWithDeliveryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatWithDeliveryScreen(viewModel: ChatWithDeliveryViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { number ->
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${number}"))
            context.startActivity(intent)
        }
    }
    ChatWithDeliveryContent(
        state = state, action = viewModel::onActionTrigger
    )
}

@Composable
fun ChatWithDeliveryContent(
    state: ChatWithDeliveryContract.State, action: (ChatWithDeliveryContract.Action) -> Unit
) {

    val listState = rememberLazyListState()

    LaunchedEffect(state.chatMessages.size) {
        if (state.chatMessages.isNotEmpty()) {
            listState.animateScrollToItem(state.chatMessages.lastIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh)
            .statusBarsPadding()
            .navigationBarsPadding(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        DelivaryUserTopBar(onStartIconClicked = {
            action(ChatWithDeliveryContract.Action.NavigateBacKClicked)
        }, endIcon = R.drawable.img_phone, onEndIconClicked = {
            action(ChatWithDeliveryContract.Action.OnCallDriverClicked)
        }, rowContent = {
            AsyncImage(
                model = state.deliveryImage,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
                    .width(40.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.img_default_user_account)
            )

            Text(
                modifier = Modifier.padding(start = 7.dp),
                text = state.deliveryName,
                color = DelivaryUserTheme.colors.background.surfaceHigh,
                style = DelivaryUserTheme.typography.headline.extraSmall
            )

        })

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = DelivaryUserTheme.colors.background.surfaceHigh),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            reverseLayout = false
        ) {
            items(
                items = state.chatMessages, key = { it.id }) { message ->
                ChatMessageBubble(message = message)
            }
        }

        ChatInputField(messageValue = state.message.value, onMessageChange = { newValue ->
            action(ChatWithDeliveryContract.Action.MessageChanged(newValue))
        }, onSendClick = {
            action(ChatWithDeliveryContract.Action.SendMessageClicked)

        })
    }
}