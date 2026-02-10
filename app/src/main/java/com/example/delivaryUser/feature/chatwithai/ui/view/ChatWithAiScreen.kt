package com.example.delivaryUser.feature.chatwithai.ui.view

import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.delivaryUser.R
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.components.screen.DelivaryUserScreen
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.example.delivaryUser.feature.chatwithai.ui.viewmodel.ChatWithAiContract
import com.example.delivaryUser.feature.chatwithai.ui.viewmodel.ChatWithAiViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatWithAiScreen(viewModel: ChatWithAiViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(ChatWithAiContract.Action.Init)
    }
    ChatWithAiContent(state = state, action = viewModel::onActionTrigger)
}

@Composable
private fun ChatWithAiContent(state: ChatWithAiContract.State, action: (ChatWithAiContract.Action) -> Unit) {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar(
                onStartIconClicked = { action((ChatWithAiContract.Action.OnBackClicked)) },
                content = {
                    Text(
                        stringResource(R.string.ai),
                        style = DelivaryUserTheme.typography.headline.large,
                        color = DelivaryUserTheme.colors.background.surfaceHigh
                    )
                },
            )
        }) {
        ChatWithAiWebView(html = state.link)
    }
}

@Composable
private fun ChatWithAiWebView(
    html: String,
    modifier: Modifier = Modifier,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    databaseEnabled = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }

                webViewClient = WebViewClient()

                loadDataWithBaseURL(
                    "https://delivery-online.com/",
                    html,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(
                "https://delivery-online.com/",
                html,
                "text/html",
                "UTF-8",
                null
            )
        }
    )
}

@Composable
@PreviewAllVariants
private fun ChatWithAiScreenPreview() = DelivaryUserTheme {
    ChatWithAiContent(state = ChatWithAiContract.State(), action = {})
}