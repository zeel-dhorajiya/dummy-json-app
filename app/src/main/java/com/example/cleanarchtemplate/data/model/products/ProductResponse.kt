package com.example.cleanarchtemplate.data.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
  val products: List<Product> = emptyList(),
  val total: Int = 0,
  val skip: Int = 0,
  val limit: Int = 0,
)
