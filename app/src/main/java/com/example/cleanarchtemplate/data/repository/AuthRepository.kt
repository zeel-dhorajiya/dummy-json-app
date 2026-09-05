package com.example.cleanarchtemplate.data.repository

import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.model.auth.LoginRequest
import com.example.cleanarchtemplate.data.model.auth.LoginResponse
import com.example.cleanarchtemplate.data.model.auth.User

interface AuthRepository {
  suspend fun login(username: String, password: String): LoginResponse
  suspend fun getMe(): User
}

class DefaultAuthRepository(
  private val apiService: ApiService,
) : AuthRepository {

  override suspend fun login(username: String, password: String): LoginResponse =
    apiService.login(LoginRequest(username = username, password = password))

  override suspend fun getMe(): User = apiService.getMe()
}