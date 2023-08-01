package com.kakapo.justchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kakapo.auth.login.navigation.LOGIN_ROUTE
import com.kakapo.auth.login.navigation.loginScreen
import com.kakapo.conversation.navigation.conversationScreen
import com.kakapo.conversation.navigation.navigateToConversationScreen

@Composable
internal fun JustChatNavHost(
    startDestination: String = LOGIN_ROUTE
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        loginScreen(
            navigateToNextDestination = {
                navController.navigateToConversationScreen()
            }
        )
        conversationScreen()
    }
}