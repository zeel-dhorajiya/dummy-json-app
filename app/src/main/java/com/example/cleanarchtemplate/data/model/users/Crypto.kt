package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
  val coin: String? = null,
  val wallet: String? = null,
  val network: String? = null,
)