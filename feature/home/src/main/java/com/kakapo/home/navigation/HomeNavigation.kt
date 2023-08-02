package com.kakapo.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.home.HomeRoute

const val HOME_ROUTE = "home_screen"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null){
    navigate(HOME_ROUTE, navOptions)
}

fun NavGraphBuilder.homeScreen(navigateToConversationScreen: (String) -> Unit){
    composable(HOME_ROUTE){
        HomeRoute(onChatItemClick = navigateToConversationScreen)
    }
}