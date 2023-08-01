package com.kakapo.justchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kakapo.designsystem.theme.JustChatTheme
import com.kakapo.justchat.navigation.JustChatNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JustChatTheme {
                JustChatNavHost()
            }
        }
    }
}
