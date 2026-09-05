package com.example.cleanarchtemplate.data.model.users

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
  val users: List<UserProfile> = emptyList(),
  val total: Int = 0,
  val skip: Int = 0,
  val limit: Int = 0,
)