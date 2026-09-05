package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Bank(
  @SerialName("cardExpire") val cardExpire: String? = null,
  @SerialName("cardNumber") val cardNumber: String? = null,
  @SerialName("cardType") val cardType: String? = null,
  val currency: String? = null,
  val iban: String? = null,
)