package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Hair(
  val color: String? = null,
  val type: String? = null,
)