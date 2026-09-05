package com.example.cleanarchtemplate.data.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
  val id: Int? = null,
  val username: String? = null,
  val email: String? = null,
  val firstName: String? = null,
  val lastName: String? = null,
  val gender: String? = null,
  val image: String? = null,
  @SerialName("accessToken") val accessToken: String? = null,
  @SerialName("refreshToken") val refreshToken: String? = null,
)
