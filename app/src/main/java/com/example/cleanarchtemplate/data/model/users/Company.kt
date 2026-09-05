package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Company(
  val department: String? = null,
  val name: String? = null,
  val title: String? = null,
  val address: Address? = null,
)