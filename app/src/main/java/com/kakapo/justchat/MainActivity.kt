package com.kakapo.justchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.designsystem.theme.JustChatTheme
import com.kakapo.justchat.navigation.JustChatNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val splashScreen = installSplashScreen()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            splashScreen.setKeepOnScreenCondition{ uiState.loading }
            JustChatTheme {
                JustChatNavHost(startDestination = uiState.startDestination)
            }
        }
    }
}
