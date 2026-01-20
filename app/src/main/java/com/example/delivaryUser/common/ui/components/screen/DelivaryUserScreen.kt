package com.example.delivaryUser.common.ui.components.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.delivaryUser.common.ui.components.bars.topbar.DelivaryUserTopBar
import com.example.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme

@Composable
fun DelivaryUserScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    scrollState: ScrollState? = null,
    contentScrollState: ScrollState? = null,
    isImePaddingEnabled: Boolean = true,
    contentVerticalArrangement: Arrangement.Vertical = Arrangement.Top,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    header: @Composable () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    val scrollModifier = if (scrollState != null) Modifier.verticalScroll(scrollState) else Modifier
    val contentScrollModifier =
        if (contentScrollState != null && scrollState == null) Modifier.verticalScroll(contentScrollState) else Modifier

    val imePaddingModifier = if (isImePaddingEnabled) Modifier.imePadding() else Modifier

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(scrollModifier)
            .then(modifier)
            .background(color = DelivaryUserTheme.colors.background.surfaceHigh),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        header()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(contentScrollModifier)
                .padding(
                    start = DelivaryUserScaffold.calculateStartPadding(),
                    end = DelivaryUserScaffold.calculateEndPadding(),
                )
                .then(imePaddingModifier)
                .padding(contentPadding),
            verticalArrangement = contentVerticalArrangement,
            horizontalAlignment = contentHorizontalAlignment,
            content = content
        )
    }

}

@PreviewAllVariants
@Composable
private fun DelivaryUserScreenPreview() = DelivaryUserTheme {
    DelivaryUserScreen(
        header = {
            DelivaryUserTopBar({})
        },
        content = {}
    )
}