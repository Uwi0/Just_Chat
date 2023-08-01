package com.kakapo.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.auth.R
import com.kakapo.designsystem.component.userInput.DefaultTextField
import com.kakapo.ui.component.ButtonLarge
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToNextDestination: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = Unit) {
        viewModel.uiSideEffect.collectLatest {
            when (it) {
                LoginUiEffect.LoginUser -> {
                    navigateToNextDestination.invoke()
                }
            }
        }
    }
    LoginScreen(
        uiState,
        onUsernameChanged = viewModel::onChangeUsername,
        onLoginClicked = viewModel::saveUsername
    )
}

@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    onUsernameChanged: (String) -> Unit,
    onLoginClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Just Chat")
        DefaultTextField(
            query = uiState.username,
            onQueryChanged = onUsernameChanged,
            hint = R.string.input_your_username
        )
        Spacer(modifier = Modifier.size(16.dp))
        ButtonLarge(onClick = onLoginClicked, textButton = R.string.login)
    }
}