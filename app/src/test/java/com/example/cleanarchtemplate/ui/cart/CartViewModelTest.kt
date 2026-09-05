package com.example.cleanarchtemplate.ui.cart

import com.example.cleanarchtemplate.InMemorySharedPreferences
import com.example.cleanarchtemplate.data.local.TokenManager
import com.example.cleanarchtemplate.data.model.cart.Cart
import com.example.cleanarchtemplate.data.model.cart.CartResponse
import com.example.cleanarchtemplate.data.repository.CartRepository
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
class CartViewModelTest {

  @Before
  fun setUp() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `loadUserCart success sets success state with the first cart`() = runTest {
    val cart = Cart(id = 1, total = 99.99, userId = 5)
    val viewModel = CartViewModel(
      FakeCartRepository(response = CartResponse(carts = listOf(cart))),
      TokenManager(InMemorySharedPreferences()),
    )

    assertEquals(CartState.Success(cart), viewModel.state.value)
  }

  @Test
  fun `loadUserCart with empty carts sets success state with null`() = runTest {
    val viewModel = CartViewModel(
      FakeCartRepository(response = CartResponse(carts = emptyList())),
      TokenManager(InMemorySharedPreferences()),
    )

    assertEquals(CartState.Success(null), viewModel.state.value)
  }

  @Test
  fun `loadUserCart error sets error state`() = runTest {
    val viewModel = CartViewModel(
      FakeCartRepository(throwError = true),
      TokenManager(InMemorySharedPreferences()),
    )

    assertEquals(CartState.Error("cart failed"), viewModel.state.value)
  }

  @Test
  fun `loadUserCart can be reloaded and updates state`() = runTest {
    val repository = FakeCartRepository(response = CartResponse(carts = emptyList()))
    val viewModel = CartViewModel(repository, TokenManager(InMemorySharedPreferences()))

    val cart = Cart(id = 2, total = 10.0, userId = 5)
    repository.response = CartResponse(carts = listOf(cart))
    viewModel.loadUserCart()

    assertEquals(CartState.Success(cart), viewModel.state.value)
  }

  private class FakeCartRepository(
    var response: CartResponse = CartResponse(carts = emptyList()),
    private val throwError: Boolean = false,
  ) : CartRepository {

    override suspend fun getUserCarts(userId: Int): CartResponse {
      if (throwError) throw RuntimeException("cart failed")
      assertEquals(5, userId)
      return response
    }

    override suspend fun getCarts(limit: Int, skip: Int, select: String?): CartResponse = throw NotImplementedError()
    override suspend fun getCart(id: Int): Cart = throw NotImplementedError()
  }
}
