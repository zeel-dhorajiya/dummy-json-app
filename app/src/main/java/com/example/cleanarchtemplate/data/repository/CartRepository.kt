package com.example.cleanarchtemplate.data.repository

import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.model.cart.Cart
import com.example.cleanarchtemplate.data.model.cart.CartResponse

interface CartRepository {
  suspend fun getCarts(limit: Int = 30, skip: Int = 0, select: String? = null): CartResponse
  suspend fun getCart(id: Int): Cart
  suspend fun getUserCarts(userId: Int): CartResponse
}

class DefaultCartRepository(
  private val apiService: ApiService,
) : CartRepository {

  override suspend fun getCarts(limit: Int, skip: Int, select: String?): CartResponse =
    apiService.getCarts(limit = limit, skip = skip, select = select)

  override suspend fun getCart(id: Int): Cart = apiService.getCart(id)

  override suspend fun getUserCarts(userId: Int): CartResponse =
    apiService.getUserCarts(userId)
}