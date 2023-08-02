package com.kakapo.justchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.auth.login.navigation.LOGIN_ROUTE
import com.kakapo.common.result.asResult
import com.kakapo.common.result.subscribe
import com.kakapo.conversation.navigation.CONVERSATION_ROUTE
import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.home.navigation.HOME_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(MainUiState())

    init {
        checkIsUsernameIsNotEmpty()
    }

    private fun checkIsUsernameIsNotEmpty() = viewModelScope.launch {
        authRepository.getSavedUsername().asResult().subscribe(
            onLoading = { _uiState.update { it.copy(loading = true) } },
            onSuccess = { username ->
                val destination = if (username != "") LOGIN_ROUTE else HOME_ROUTE
                _uiState.update { it.copy(startDestination = LOGIN_ROUTE) }
            },
            onError = {
                _uiState.update { it.copy(loading = false) }
            }
        )
        delay(2000)
        _uiState.update { it.copy(loading = false) }
    }
}


data class MainUiState(
    val loading: Boolean = false,
    val startDestination: String = LOGIN_ROUTE
)