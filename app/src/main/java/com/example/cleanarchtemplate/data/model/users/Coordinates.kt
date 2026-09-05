package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
  val lat: Double? = null,
  val lng: Double? = null,
)