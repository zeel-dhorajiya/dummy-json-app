package com.example.cleanarchtemplate.data.model.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartResponse(
  val carts: List<Cart> = emptyList(),
  val total: Int = 0,
  val skip: Int = 0,
  val limit: Int = 0,
)