package com.hyperdesign.delivaryUser.feature.authentication.splash.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.hyperdesign.delivaryUser.R
import com.hyperdesign.delivaryUser.common.ui.components.preview.PreviewAllVariants
import com.hyperdesign.delivaryUser.common.ui.theme.DelivaryUserTheme
import com.hyperdesign.delivaryUser.feature.authentication.splash.ui.viewmodel.SplashScreenContract
import com.hyperdesign.delivaryUser.feature.authentication.splash.ui.viewmodel.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(viewModel: SplashScreenViewModel = koinViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.onActionTrigger(SplashScreenContract.Action.Init)
    }
    SplashScreenContent()
}

@Composable
private fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DelivaryUserTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.img_splash_logo), contentDescription = null)
    }
}

@Composable
@PreviewAllVariants
private fun SplashScreenPreview() = DelivaryUserTheme {
    SplashScreenContent()
}