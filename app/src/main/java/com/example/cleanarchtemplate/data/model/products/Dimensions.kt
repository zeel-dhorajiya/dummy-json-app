package com.example.cleanarchtemplate.data.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
  val width: Double? = null,
  val height: Double? = null,
  val depth: Double? = null,
)
