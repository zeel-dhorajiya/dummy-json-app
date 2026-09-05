package com.example.cleanarchtemplate.data.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
  @SerialName("createdAt") val createdAt: String? = null,
  @SerialName("updatedAt") val updatedAt: String? = null,
  @SerialName("barcode") val barcode: String? = null,
  @SerialName("qrCode") val qrCode: String? = null,
)
