package com.example.cleanarchtemplate.data.api

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object NetworkModule {

  const val BASE_URL = "https://dummyjson.com/"

  val json: Json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
  }

  fun provideJson(): Json = json

  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(loggingInterceptor)
    .build()

  fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BASIC
    }

  fun provideRetrofit(
    json: Json,
    okHttpClient: OkHttpClient,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

  fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
}
