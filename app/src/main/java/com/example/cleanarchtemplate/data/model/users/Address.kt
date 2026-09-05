package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
  val address: String? = null,
  val city: String? = null,
  val state: String? = null,
  @SerialName("stateCode") val stateCode: String? = null,
  @SerialName("postalCode") val postalCode: String? = null,
  val coordinates: Coordinates? = null,
  val country: String? = null,
)