package com.kakapo.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.common.result.asResult
import com.kakapo.common.result.subscribe
import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.data.repository.base.ChatSocketRepository
import com.kakapo.data.repository.base.MessageRepository
import com.kakapo.logger.Logger
import com.kakapo.model.chat.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val chatSocketRepository: ChatSocketRepository,
    private val messagesRepository: MessageRepository,
) : ViewModel() {

    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(ConversationUiState())

    fun connectToChatSocket(){
        getSavedProfileUser()
        getAllMessage()
    }

    private fun getSavedProfileUser() {
        viewModelScope.launch {
            authRepository.getSavedUsername().asResult().subscribe(
                onSuccess = { username ->
                    _uiState.update { it.copy(username = username) }
                    initSessionChat(username)
                }
            )
        }
    }

    private fun initSessionChat(username: String) {
        viewModelScope.launch {
            chatSocketRepository.iniSession(username).asResult().subscribe(
                onSuccess = {
                    observeMessage()
                    Logger.d("success_init_session")
                },
                onError = {
                    Logger.e(it, "error_init_session")
                }
            )
        }
    }

    private fun observeMessage() {
        chatSocketRepository.observeInComingMessage().onEach { message ->
            val newList = uiState.value.messages.toMutableList().apply {
                add(0, message)
            }
            _uiState.update { it.copy(messages = newList) }
        }.launchIn(viewModelScope)
    }

    fun sendMessage(message: String){
        Logger.d("message_send: $message")
        viewModelScope.launch {
            chatSocketRepository.sendMessage(message).asResult().subscribe(
                onSuccess = {
                    Logger.d("success_send_message")
                },
                onError = {
                    Logger.e(it,"error_send_message")
                }
            )
        }
    }

    fun disconnectChatSession() {
        viewModelScope.launch {
            chatSocketRepository.closeSession().asResult().subscribe(
                onSuccess = {
                    Logger.d("success_send_message")
                },
                onError = {
                    Logger.e(it, "error_send_message")
                }
            )
        }
    }

    private fun getAllMessage() {
        viewModelScope.launch {
            messagesRepository.getAllMessages().asResult().subscribe(
                onSuccess = { messages -> _uiState.update { it.copy(messages = messages) } }
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnectChatSession()
    }
}

data class ConversationUiState(
    val username: String = "",
    val messages: List<Message> = emptyList()

)