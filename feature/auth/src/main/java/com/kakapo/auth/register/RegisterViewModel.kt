package com.kakapo.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor() : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiSideEffect get() = _uiSideEffect.asSharedFlow()
    private val _uiSideEffect = MutableSharedFlow<RegisterSideEffect>()

    fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String){
        _uiState.update { it.copy(password = password) }
    }

    fun onConfirmedPasswordChanged(confirmedPassword: String){
        _uiState.update { it.copy(confirmedPassword = confirmedPassword) }
    }

    fun onRegisterButtonClicked(){
        viewModelScope.launch {
            _uiSideEffect.emit(RegisterSideEffect.NavigateToLoginScreen)
        }
    }
}

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = ""
)

sealed interface RegisterSideEffect{
    object NavigateToLoginScreen: RegisterSideEffect
}