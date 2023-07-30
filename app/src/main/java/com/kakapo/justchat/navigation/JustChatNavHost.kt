package com.kakapo.justchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kakapo.conversation.navigation.CONVERSATION_ROUTE
import com.kakapo.conversation.navigation.conversationScreen

@Composable
internal fun JustChatNavHost(
    startDestination: String = CONVERSATION_ROUTE
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        conversationScreen()
    }
}