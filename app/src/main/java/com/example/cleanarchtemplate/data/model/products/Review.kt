package com.example.cleanarchtemplate.data.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
  val rating: Double? = null,
  val comment: String? = null,
  val date: String? = null,
  @SerialName("reviewerName") val reviewerName: String? = null,
  @SerialName("reviewerEmail") val reviewerEmail: String? = null,
)
