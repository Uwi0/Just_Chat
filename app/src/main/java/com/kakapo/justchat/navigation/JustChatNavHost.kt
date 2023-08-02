package com.kakapo.justchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kakapo.auth.login.navigation.LOGIN_ROUTE
import com.kakapo.auth.login.navigation.loginScreen
import com.kakapo.auth.register.navigation.navigateToRegisterScreen
import com.kakapo.auth.register.navigation.registerScreen
import com.kakapo.conversation.navigation.conversationScreen
import com.kakapo.conversation.navigation.navigateToConversationScreen
import com.kakapo.home.navigation.homeScreen

@Composable
internal fun JustChatNavHost(
    startDestination: String = LOGIN_ROUTE,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        loginScreen(
            navigateToNextDestination = {
                navController.navigateToConversationScreen()
            },
            navigateToRegisterScreen = {
                navController.navigateToRegisterScreen()
            }
        )
        registerScreen(
            navigateToLoginScreen = {
                navController.popBackStack()
            }
        )
        homeScreen(
            navigateToConversationScreen = {
                navController.navigateToConversationScreen()
            }
        )
        conversationScreen()
    }
}