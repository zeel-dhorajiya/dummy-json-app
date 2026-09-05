package com.example.cleanarchtemplate.data.model.cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
  val id: Int? = null,
  val title: String? = null,
  val price: Double? = null,
  val quantity: Int? = null,
  val total: Double? = null,
  @SerialName("discountPercentage") val discountPercentage: Double? = null,
  @SerialName("discountedTotal") val discountedTotal: Double? = null,
  val thumbnail: String? = null,
)