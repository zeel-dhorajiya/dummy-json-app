package com.example.cleanarchtemplate.ui.main

import com.example.cleanarchtemplate.data.model.products.Product
import com.example.cleanarchtemplate.data.model.products.ProductResponse
import com.example.cleanarchtemplate.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

  @Before
  fun setUp() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `loadProducts success sets success state with products`() = runTest {
    val products = listOf(Product(id = 1, title = "Keyboard"), Product(id = 2, title = "Mouse"))
    val viewModel = ProductListViewModel(
      FakeProductRepository(response = ProductResponse(products = products, total = 2))
    )

    assertEquals(ProductListState.Success(products), viewModel.state.value)
  }

  @Test
  fun `loadProducts error sets error state`() = runTest {
    val viewModel = ProductListViewModel(FakeProductRepository(throwError = true))

    assertEquals(ProductListState.Error("products failed"), viewModel.state.value)
  }

  @Test
  fun `loadProducts can be reloaded and updates state`() = runTest {
    val repository = FakeProductRepository(response = ProductResponse(products = emptyList()))
    val viewModel = ProductListViewModel(repository)

    repository.response = ProductResponse(products = listOf(Product(id = 9, title = "Monitor")))
    viewModel.loadProducts()

    assertEquals(ProductListState.Success(listOf(Product(id = 9, title = "Monitor"))), viewModel.state.value)
  }

  private class FakeProductRepository(
    var response: ProductResponse = ProductResponse(products = emptyList()),
    private val throwError: Boolean = false,
  ) : ProductRepository {

    override suspend fun getProducts(limit: Int, skip: Int, select: String?): ProductResponse {
      if (throwError) throw RuntimeException("products failed")
      return response
    }

    override suspend fun getProduct(id: Int): Product = throw NotImplementedError()
    override suspend fun searchProducts(query: String): ProductResponse = throw NotImplementedError()
  }
}
