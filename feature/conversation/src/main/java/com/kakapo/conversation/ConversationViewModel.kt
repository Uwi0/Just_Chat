package com.kakapo.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.common.result.asResult
import com.kakapo.common.result.subscribe
import com.kakapo.data.repository.base.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(ConversationUiState())

    init {
        getSavedProfileUser()
    }

    private fun getSavedProfileUser() {
        viewModelScope.launch {
            authRepository.getSavedUsername().asResult().subscribe(
                onSuccess = { username ->
                    _uiState.update { it.copy(username = username) }
                }
            )
        }
    }
}

data class ConversationUiState(
    val username: String = ""
)