package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
  val id: Int? = null,
  @SerialName("firstName") val firstName: String? = null,
  @SerialName("lastName") val lastName: String? = null,
  @SerialName("maidenName") val maidenName: String? = null,
  val age: Int? = null,
  val gender: String? = null,
  val email: String? = null,
  val phone: String? = null,
  val username: String? = null,
  val password: String? = null,
  @SerialName("birthDate") val birthDate: String? = null,
  val image: String? = null,
  @SerialName("bloodGroup") val bloodGroup: String? = null,
  val height: Double? = null,
  val weight: Double? = null,
  @SerialName("eyeColor") val eyeColor: String? = null,
  val hair: Hair? = null,
  val ip: String? = null,
  val address: Address? = null,
  @SerialName("macAddress") val macAddress: String? = null,
  val university: String? = null,
  val bank: Bank? = null,
  val company: Company? = null,
  val ein: String? = null,
  val ssn: String? = null,
  @SerialName("userAgent") val userAgent: String? = null,
  val crypto: Crypto? = null,
  val role: String? = null,
)