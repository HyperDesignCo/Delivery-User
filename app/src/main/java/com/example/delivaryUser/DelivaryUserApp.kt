package com.example.delivaryUser

import android.app.LocaleManager
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.delivaryUser.common.ui.components.bars.navigationbar.DelivaryUserNavigationBar
import com.example.delivaryUser.common.ui.components.dialogs.DelivaryUserLoadingDialog
import com.example.delivaryUser.common.ui.eventcontroller.IEventController
import com.example.delivaryUser.common.ui.extension.ObserveAsEvents
import com.example.delivaryUser.common.ui.extension.UIText
import com.example.delivaryUser.common.ui.language.ILanguageEvent
import com.example.delivaryUser.common.ui.loading.ILoadingEvent
import com.example.delivaryUser.common.ui.message.IMessageEvent
import com.example.delivaryUser.common.ui.navigation.BottomDestination
import com.example.delivaryUser.common.ui.navigation.INavigator
import com.example.delivaryUser.common.ui.navigation.NavigationEvent
import com.example.delivaryUser.common.ui.navigation.buildNavAccountGraph
import com.example.delivaryUser.common.ui.navigation.buildNavAuthGraph
import com.example.delivaryUser.common.ui.navigation.buildNavMainGraph
import com.example.delivaryUser.common.ui.navigation.buildNavOrderGraph
import com.example.delivaryUser.common.ui.theme.DelivaryUserTheme
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

val LocalPadding = compositionLocalOf<PaddingValues> { PaddingValues() }

@Composable
fun DelivaryUserApp(
    navigator: INavigator = koinInject(),
    navHostController: NavHostController = rememberNavController(),
) {
    DelivaryUserTheme {
        ObserveLanguageEvent()
        ObserveMessageEvent()
        ObserveLoadingEvent()
        ObserveAsEvents(navigator.navigationEvent) { event ->
            when (event) {
                is NavigationEvent.Navigate -> navHostController.navigate(
                    route = event.destination, builder = event.builder
                )

                is NavigationEvent.NavigateUp -> navHostController.navigateUp()
            }
        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                DelivaryUserNavigationBar(
                    navController = navHostController, destinations = BottomDestination.destinations,
                    onFloatingButtonClick = {}
                )
            }
        ) { innerPadding ->
            CompositionLocalProvider(LocalPadding provides innerPadding) {
                NavHost(
                    navController = navHostController,
                    startDestination = navigator.startGraph,
                ) {
                    buildNavAuthGraph()
                    buildNavMainGraph()
                    buildNavOrderGraph()
                    buildNavAccountGraph()
                }
            }
        }
    }
}

@Composable
private fun ObserveMessageEvent() {
    val context = LocalContext.current
    fun UIText.toMessageString(): String {
        return when (this) {
            is UIText.DynamicString -> this.value
            is UIText.StringResource -> context.getString(this.id)
        }
    }

    val messageEvent: IEventController<IMessageEvent> = koinInject(qualifier = named("MessageEvent"))
    ObserveAsEvents(messageEvent.event) { event ->
        when (event) {
            is IMessageEvent.Toast -> {
                Toast.makeText(context, event.message.toMessageString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
private fun ObserveLoadingEvent() {
    val loadingEvent: IEventController<ILoadingEvent> = koinInject(qualifier = named("LoadingEvent"))
    var isLoading by remember { mutableStateOf(false) }
    ObserveAsEvents(loadingEvent.event) { event ->
        Log.d("LoadingEvent", "Event received: $event")
        when (event) {
            is ILoadingEvent.CircularProgressIndicator -> isLoading = event.isLoading
        }
    }
    if (isLoading) DelivaryUserLoadingDialog()
}
@Composable
fun ObserveLanguageEvent() {
    val context = LocalContext.current
    val languageEvent: IEventController<ILanguageEvent> =
        koinInject(qualifier = named("LanguageEvent"))

    ObserveAsEvents(languageEvent.event) { event ->
        when (event) {
            is ILanguageEvent.ChangeLanguage -> {
                val compatLocales =
                    LocaleListCompat.forLanguageTags(event.languageCode)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val localeManager = context.getSystemService(LocaleManager::class.java)
                    val platformLocales = compatLocales.unwrap()
                    localeManager.applicationLocales = platformLocales as LocaleList
                } else {
                    AppCompatDelegate.setApplicationLocales(compatLocales)
                }
            }
        }
    }
}