package com.example.cleanarchtemplate.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtemplate.data.model.products.Product
import com.example.cleanarchtemplate.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Success(val product: Product) : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
}

class ProductDetailViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val state: StateFlow<ProductDetailState> = _state

    fun loadProduct(productId: Int) {
        _state.value = ProductDetailState.Loading
        viewModelScope.launch {
            try {
                val product = productRepository.getProduct(productId)
                _state.value = ProductDetailState.Success(product)
            } catch (e: Exception) {
                _state.value = ProductDetailState.Error(e.message ?: "Failed to load product details")
            }
        }
    }
}
