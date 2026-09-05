package com.example.cleanarchtemplate.data.model.products

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
  val id: Int? = null,
  val title: String? = null,
  val description: String? = null,
  val category: String? = null,
  val price: Double? = null,
  @SerialName("discountPercentage") val discountPercentage: Double? = null,
  val rating: Double? = null,
  val stock: Int? = null,
  val tags: List<String>? = null,
  val brand: String? = null,
  val sku: String? = null,
  val weight: Double? = null,
  val dimensions: Dimensions? = null,
  @SerialName("warrantyInformation") val warrantyInformation: String? = null,
  @SerialName("shippingInformation") val shippingInformation: String? = null,
  @SerialName("availabilityStatus") val availabilityStatus: String? = null,
  val reviews: List<Review>? = null,
  @SerialName("returnPolicy") val returnPolicy: String? = null,
  @SerialName("minimumOrderQuantity") val minimumOrderQuantity: Int? = null,
  val meta: Meta? = null,
  val thumbnail: String? = null,
  val images: List<String>? = null,
)
