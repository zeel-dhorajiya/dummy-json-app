package com.example.cleanarchtemplate.data.model.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(
  val id: Int? = null,
  val products: List<CartProduct> = emptyList(),
  val total: Double? = null,
  @SerialName("discountedTotal") val discountedTotal: Double? = null,
  @SerialName("userId") val userId: Int? = null,
  @SerialName("totalProducts") val totalProducts: Int? = null,
  @SerialName("totalQuantity") val totalQuantity: Int? = null,
)