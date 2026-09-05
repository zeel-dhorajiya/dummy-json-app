package com.example.cleanarchtemplate.data.repository

import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.model.products.Product
import com.example.cleanarchtemplate.data.model.products.ProductResponse

interface ProductRepository {
  suspend fun getProducts(limit: Int = 30, skip: Int = 0, select: String? = null): ProductResponse
  suspend fun getProduct(id: Int): Product
  suspend fun searchProducts(query: String): ProductResponse
}

class DefaultProductRepository(
  private val apiService: ApiService,
) : ProductRepository {

  override suspend fun getProducts(limit: Int, skip: Int, select: String?): ProductResponse =
    apiService.getProducts(limit = limit, skip = skip, select = select)

  override suspend fun getProduct(id: Int): Product = apiService.getProduct(id)

  override suspend fun searchProducts(query: String): ProductResponse =
    apiService.searchProducts(query)
}