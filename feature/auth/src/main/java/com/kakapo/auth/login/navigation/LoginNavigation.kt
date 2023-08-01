package com.kakapo.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.auth.login.LoginRoute

const val LOGIN_ROUTE = "login_screen"

fun NavController.navigateToLogin(navOptions: NavOptions? = null){
    navigate(LOGIN_ROUTE, navOptions)
}

fun NavGraphBuilder.loginScreen(navigateToNextDestination: () -> Unit){
    composable(LOGIN_ROUTE){
        LoginRoute(navigateToNextDestination = navigateToNextDestination)
    }
}