package com.kakapo.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.common.result.asResult
import com.kakapo.common.result.suspendSubscribe
import com.kakapo.data.repository.base.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(LoginUiState())

    val uiSideEffect get() = _uiSideEffect.asSharedFlow()
    private val _uiSideEffect = MutableSharedFlow<LoginUiEffect>()

    fun onChangeUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun saveUsername() {
        viewModelScope.launch {
            authRepository.authenticateUserLogin(uiState.value.username).asResult().suspendSubscribe(
                onSuccess = {
                    _uiSideEffect.emit(LoginUiEffect.LoginUser)
                }
            )
        }
    }
}

data class LoginUiState(
    val username: String = ""
)

sealed interface LoginUiEffect{
    object LoginUser: LoginUiEffect
}