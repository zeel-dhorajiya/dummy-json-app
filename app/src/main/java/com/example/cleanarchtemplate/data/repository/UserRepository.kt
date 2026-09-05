package com.example.cleanarchtemplate.data.repository

import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.model.users.UserProfile
import com.example.cleanarchtemplate.data.model.users.UsersResponse

interface UserRepository {
  suspend fun getUsers(limit: Int = 30, skip: Int = 0, select: String? = null): UsersResponse
  suspend fun getUser(id: Int): UserProfile
  suspend fun searchUsers(query: String): UsersResponse
}

class DefaultUserRepository(
  private val apiService: ApiService,
) : UserRepository {

  override suspend fun getUsers(limit: Int, skip: Int, select: String?): UsersResponse =
    apiService.getUsers(limit = limit, skip = skip, select = select)

  override suspend fun getUser(id: Int): UserProfile = apiService.getUser(id)

  override suspend fun searchUsers(query: String): UsersResponse =
    apiService.searchUsers(query)
}