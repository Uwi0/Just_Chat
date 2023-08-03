package com.kakapo.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakapo.common.result.asResult
import com.kakapo.common.result.suspendSubscribe
import com.kakapo.data.model.param.RegisterParam
import com.kakapo.data.repository.base.AuthRepository
import com.kakapo.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

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

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onConfirmedPasswordChanged(confirmedPassword: String) {
        _uiState.update { it.copy(confirmedPassword = confirmedPassword) }
    }

    fun onRegisterButtonClicked() {
        if (validateUserInput())
            registerNewUser()
    }

    private fun registerNewUser() {
        viewModelScope.launch {
            authRepository.authenticateUserRegister(uiState.value.asRegisterParam())
                .asResult()
                .suspendSubscribe(
                    onLoading = {
                        _uiState.update { it.copy(loading = true) }
                    },
                    onSuccess = {
                        _uiState.update { it.copy(loading = false) }
                        _uiSideEffect.emit(RegisterSideEffect.NavigateToLoginScreen)
                    },
                    onError = { error ->
                        _uiState.update { it.copy(loading = false) }
                        _uiSideEffect.emit(RegisterSideEffect.ShowError(error?.message ?: "Error"))
                        Logger.e(error, "Error_register_user ${error?.message}")
                    }
                )
        }
    }

    private fun validateUserInput(): Boolean {
        val validationConditions = listOf(
            uiState.value.isUsernameNotEmpty to "Username can't be empty",
            uiState.value.isEmailNotEmpty to "Email can't be empty",
            uiState.value.isEmailFormatCorrect to "Email format is incorrect",
            uiState.value.isPasswordNotEmpty to "Password can't be empty",
            uiState.value.isPasswordLengthEnough to "Your password is less than 8 characters",
            uiState.value.isPasswordStrongEnough to "Your password doesn't contain characters, numbers, or capital letters",
            uiState.value.isPasswordAndConfirmedPasswordAreSame to "Password and Confirmed Password are not the same"
        )

        for ((condition, errorMessage) in validationConditions) {
            if (!condition) {
                viewModelScope.launch {
                    _uiSideEffect.emit(RegisterSideEffect.ShowError(errorMessage))
                }
                return false
            }
        }

        return true
    }
}

data class RegisterUiState(
    val loading: Boolean = false,
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
) {

    companion object {

        private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()

        private fun isEmailValid(email: String) = email.matches(EMAIL_REGEX)
        private fun isPasswordLengthEnough(password: String) = password.length >= 8

        fun isPasswordStrong(password: String): Boolean {
            val lowercaseRegex = Regex("[a-z]")
            val uppercaseRegex = Regex("[A-Z]")
            val digitRegex = Regex("\\d")
            val characterRegex = Regex("[!@#$%^&*+=]")

            val containsLowercase = lowercaseRegex.containsMatchIn(password)
            val containsUppercase = uppercaseRegex.containsMatchIn(password)
            val containsDigit = digitRegex.containsMatchIn(password)
            val containCharacter = characterRegex.containsMatchIn(password)

            return containsLowercase && containsUppercase && containsDigit && containCharacter
        }
    }


    val isUsernameNotEmpty: Boolean
        get() = username.isNotEmpty()

    val isEmailNotEmpty: Boolean
        get() = email.isNotEmpty()

    val isEmailFormatCorrect: Boolean
        get() = isEmailValid(email)

    val isPasswordNotEmpty: Boolean
        get() = password.isNotEmpty()

    val isPasswordAndConfirmedPasswordAreSame: Boolean
        get() = password == confirmedPassword

    val isPasswordLengthEnough: Boolean
        get() = isPasswordLengthEnough(password)

    val isPasswordStrongEnough: Boolean
        get() = isPasswordStrong(password)

    fun asRegisterParam() = RegisterParam(username = username, email = email, password = password)

}

sealed interface RegisterSideEffect{
    object NavigateToLoginScreen: RegisterSideEffect
    data class ShowError(val message: String) : RegisterSideEffect
}