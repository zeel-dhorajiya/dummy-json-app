package com.example.cleanarchtemplate.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchtemplate.data.model.products.Product
import com.example.cleanarchtemplate.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductListState {
    object Loading : ProductListState()
    data class Success(val products: List<Product>) : ProductListState()
    data class Error(val message: String) : ProductListState()
}

class ProductListViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ProductListState>(ProductListState.Loading)
    val state: StateFlow<ProductListState> = _state

    init {
        loadProducts()
    }

    fun loadProducts() {
        _state.value = ProductListState.Loading
        viewModelScope.launch {
            try {
                val response = productRepository.getProducts(limit = 30, skip = 0)
                _state.value = ProductListState.Success(response.products)
            } catch (e: Exception) {
                _state.value = ProductListState.Error(e.message ?: "Failed to load products")
            }
        }
    }

    fun searchProducts(query: String) {
        if (query.isBlank()) {
            loadProducts()
            return
        }
        _state.value = ProductListState.Loading
        viewModelScope.launch {
            try {
                val response = productRepository.searchProducts(query = query)
                _state.value = ProductListState.Success(response.products)
            } catch (e: Exception) {
                _state.value = ProductListState.Error(e.message ?: "Search failed")
            }
        }
    }
}
