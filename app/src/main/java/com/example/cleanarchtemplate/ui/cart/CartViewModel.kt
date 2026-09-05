package com.example.cleanarchtemplate.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtemplate.data.model.cart.Cart
import com.example.cleanarchtemplate.data.repository.CartRepository
import com.example.cleanarchtemplate.data.local.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CartState {
    object Loading : CartState()
    data class Success(val cart: Cart?) : CartState()
    data class Error(val message: String) : CartState()
}

class CartViewModel(
    private val cartRepository: CartRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state

    init {
        loadUserCart()
    }

    fun loadUserCart() {
        _state.value = CartState.Loading
        viewModelScope.launch {
            try {
                // In a real app, we'd get the userId from auth/me. 
                // Using 5 for dummy purposes as per README
                val userId = 5 
                val cartsResponse = cartRepository.getUserCarts(userId)
                
                if (cartsResponse.carts.isNotEmpty()) {
                    _state.value = CartState.Success(cartsResponse.carts.first())
                } else {
                    _state.value = CartState.Success(null)
                }
            } catch (e: Exception) {
                _state.value = CartState.Error(e.message ?: "Failed to load cart")
            }
        }
    }
}
