package com.kakapo.auth.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakapo.auth.register.RegisterRoute

const val REGISTER_ROUTE = "register_screen"

fun NavController.navigateToRegisterScreen(navOptions: NavOptions? = null){
    navigate(REGISTER_ROUTE, navOptions)
}

fun NavGraphBuilder.registerScreen(navigateToLoginScreen: () -> Unit){
    composable(REGISTER_ROUTE){
        RegisterRoute(navigateToLogin = navigateToLoginScreen)
    }
}