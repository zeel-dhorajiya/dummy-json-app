package com.example.cleanarchtemplate.ui.auth

import com.example.cleanarchtemplate.InMemorySharedPreferences
import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.local.TokenManager
import com.example.cleanarchtemplate.data.model.auth.LoginRequest
import com.example.cleanarchtemplate.data.model.auth.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

  @Before
  fun setUp() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `login with blank username returns error immediately`() = runTest {
    val viewModel = LoginViewModel(FakeApiService(), TokenManager(InMemorySharedPreferences()))

    viewModel.login(" ", "password")

    assertEquals(LoginState.Error("Username and password cannot be empty"), viewModel.loginState.value)
  }

  @Test
  fun `login with blank password returns error immediately`() = runTest {
    val viewModel = LoginViewModel(FakeApiService(), TokenManager(InMemorySharedPreferences()))

    viewModel.login("username", "  ")

    assertEquals(LoginState.Error("Username and password cannot be empty"), viewModel.loginState.value)
  }

  @Test
  fun `login success sets success state and saves token`() = runTest {
    val tokenManager = TokenManager(InMemorySharedPreferences())
    val viewModel = LoginViewModel(
      apiService = FakeApiService(loginResult = LoginResponse(accessToken = "abc-token")),
      tokenManager = tokenManager,
    )

    viewModel.login("username", "password")

    assertEquals(LoginState.Success, viewModel.loginState.value)
    assertEquals("abc-token", tokenManager.getToken())
  }

  @Test
  fun `login success with null token does not save token`() = runTest {
    val tokenManager = TokenManager(InMemorySharedPreferences())
    val viewModel = LoginViewModel(
      apiService = FakeApiService(loginResult = LoginResponse(accessToken = null)),
      tokenManager = tokenManager,
    )

    viewModel.login("username", "password")

    assertEquals(LoginState.Success, viewModel.loginState.value)
    assertNull(tokenManager.getToken())
  }

  @Test
  fun `login failure sets error state`() = runTest {
    val viewModel = LoginViewModel(
      apiService = FakeApiService(throwError = true),
      tokenManager = TokenManager(InMemorySharedPreferences()),
    )

    viewModel.login("username", "password")

    assertEquals(LoginState.Error("boom"), viewModel.loginState.value)
  }

  @Test
  fun `resetState resets to idle`() = runTest {
    val viewModel = LoginViewModel(FakeApiService(), TokenManager(InMemorySharedPreferences()))

    viewModel.resetState()

    assertEquals(LoginState.Idle, viewModel.loginState.value)
  }

  private class FakeApiService(
    private val loginResult: LoginResponse = LoginResponse(),
    private val throwError: Boolean = false,
  ) : ApiService {
    override suspend fun login(request: LoginRequest): LoginResponse {
      if (throwError) throw RuntimeException("boom")
      return loginResult
    }

    override suspend fun getMe() = throw NotImplementedError()
    override suspend fun getProducts(limit: Int, skip: Int, select: String?) = throw NotImplementedError()
    override suspend fun getProduct(id: Int) = throw NotImplementedError()
    override suspend fun searchProducts(query: String) = throw NotImplementedError()
    override suspend fun getCarts(limit: Int, skip: Int, select: String?) = throw NotImplementedError()
    override suspend fun getCart(id: Int) = throw NotImplementedError()
    override suspend fun getUserCarts(userId: Int) = throw NotImplementedError()
    override suspend fun getUsers(limit: Int, skip: Int, select: String?) = throw NotImplementedError()
    override suspend fun getUser(id: Int) = throw NotImplementedError()
    override suspend fun searchUsers(query: String) = throw NotImplementedError()
  }
}
