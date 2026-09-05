package com.example.cleanarchtemplate.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.local.TokenManager
import com.example.cleanarchtemplate.data.model.auth.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}

class LoginViewModel(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, pass: String) {
        if (username.isBlank() || pass.isBlank()) {
            _loginState.value = LoginState.Error("Username and password cannot be empty")
            return
        }

        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.login(LoginRequest(username, pass))
                response.accessToken?.let { tokenManager.saveToken(it) }
                _loginState.value = LoginState.Success
            } catch (e: Exception) {
                _loginState.value = LoginState.Error(e.message ?: "An error occurred during login")
            }
        }
    }

    fun resetState() {
        _loginState.value = LoginState.Idle
    }
}
