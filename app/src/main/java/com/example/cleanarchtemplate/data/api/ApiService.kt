package com.example.cleanarchtemplate.data.api

import com.example.cleanarchtemplate.data.model.auth.LoginRequest
import com.example.cleanarchtemplate.data.model.auth.LoginResponse
import com.example.cleanarchtemplate.data.model.auth.User
import com.example.cleanarchtemplate.data.model.cart.Cart
import com.example.cleanarchtemplate.data.model.cart.CartResponse
import com.example.cleanarchtemplate.data.model.products.Product
import com.example.cleanarchtemplate.data.model.products.ProductResponse
import com.example.cleanarchtemplate.data.model.users.UserProfile
import com.example.cleanarchtemplate.data.model.users.UsersResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

  @POST("auth/login")
  suspend fun login(@Body request: LoginRequest): LoginResponse

  @GET("auth/me")
  suspend fun getMe(): User

  @GET("products")
  suspend fun getProducts(
    @Query("limit") limit: Int = 30,
    @Query("skip") skip: Int = 0,
    @Query("select") select: String? = null,
  ): ProductResponse

  @GET("products/{id}")
  suspend fun getProduct(@Path("id") id: Int): Product

  @GET("products/search")
  suspend fun searchProducts(@Query("q") query: String): ProductResponse

  @GET("carts")
  suspend fun getCarts(
    @Query("limit") limit: Int = 30,
    @Query("skip") skip: Int = 0,
    @Query("select") select: String? = null,
  ): CartResponse

  @GET("carts/{id}")
  suspend fun getCart(@Path("id") id: Int): Cart

  @GET("carts/user/{userId}")
  suspend fun getUserCarts(@Path("userId") userId: Int): CartResponse

  @GET("users")
  suspend fun getUsers(
    @Query("limit") limit: Int = 30,
    @Query("skip") skip: Int = 0,
    @Query("select") select: String? = null,
  ): UsersResponse

  @GET("users/{id}")
  suspend fun getUser(@Path("id") id: Int): UserProfile

  @GET("users/search")
  suspend fun searchUsers(@Query("q") query: String): UsersResponse
}
