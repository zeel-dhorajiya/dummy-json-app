package com.example.cleanarchtemplate.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtemplate.data.model.users.UserProfile
import com.example.cleanarchtemplate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val profile: UserProfile) : ProfileState()
    data class Error(val message: String) : ProfileState()
}

class ProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state: StateFlow<ProfileState> = _state

    init {
        loadProfile()
    }

    fun loadProfile() {
        _state.value = ProfileState.Loading
        viewModelScope.launch {
            try {
                // Hardcoded to ID 5 as per DummyJSON testing
                val profile = userRepository.getUser(5)
                _state.value = ProfileState.Success(profile)
            } catch (e: Exception) {
                _state.value = ProfileState.Error(e.message ?: "Failed to load profile")
            }
        }
    }
}
