package com.kakapo.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakapo.auth.R
import com.kakapo.designsystem.component.JustChatTextButton
import com.kakapo.designsystem.component.userInput.DefaultTextField
import com.kakapo.designsystem.component.userInput.TextInputPassword
import com.kakapo.ui.component.ButtonLarge
import com.kakapo.ui.util.showLongToast
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun RegisterRoute(viewModel: RegisterViewModel = hiltViewModel(), navigateToLogin: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit){
        viewModel.uiSideEffect.collectLatest {
            when(it){
                RegisterSideEffect.NavigateToLoginScreen -> navigateToLogin.invoke()
                is RegisterSideEffect.ShowError -> context.showLongToast(it.message)
            }
        }
    }
    RegisterScreen(
        registerUiState = uiState,
        onUsernameChanged = viewModel::onUsernameChanged,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmedPasswordChanged = viewModel::onConfirmedPasswordChanged,
        onRegisterClicked = viewModel::onRegisterButtonClicked,
        onLoginClicked = navigateToLogin
    )
}

@Composable
internal fun RegisterScreen(
    registerUiState: RegisterUiState,
    onUsernameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmedPasswordChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Register Just Chat")
        Spacer(modifier = Modifier.size(16.dp))
        DefaultTextField(
            query = registerUiState.username,
            onQueryChanged = onUsernameChanged,
            hint = R.string.input_your_username
        )
        Spacer(modifier = Modifier.size(16.dp))
        DefaultTextField(
            query = registerUiState.email,
            onQueryChanged = onEmailChanged,
            hint = R.string.input_your_email
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextInputPassword(
            query = registerUiState.password,
            onQueryChanged = onPasswordChanged
        )
        Spacer(modifier = Modifier.size(16.dp))
        TextInputPassword(
            query = registerUiState.confirmedPassword,
            onQueryChanged = onConfirmedPasswordChanged
        )
        Spacer(modifier = Modifier.size(48.dp))
        ButtonLarge(onClick = onRegisterClicked, textButton = R.string.register)
        Spacer(modifier = Modifier.size(8.dp))
        JustChatTextButton(onClick = onLoginClicked) {
            Text(text = "already account?. Login")
        }
    }
}