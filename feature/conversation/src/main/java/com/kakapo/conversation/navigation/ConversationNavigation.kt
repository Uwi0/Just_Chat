package com.kakapo.conversation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.conversation.ConversationRoute

const val CONVERSATION_ROUTE = "conversation_route"

fun NavController.navigateToConversationScreen(navOptions: NavOptions? = null){
    this.navigate(CONVERSATION_ROUTE, navOptions)
}

fun NavGraphBuilder.conversationScreen(){
    composable(CONVERSATION_ROUTE){
        ConversationRoute()
    }
}