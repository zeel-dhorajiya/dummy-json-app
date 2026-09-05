package com.example.cleanarchtemplate.ui.profile

import com.example.cleanarchtemplate.data.model.users.UserProfile
import com.example.cleanarchtemplate.data.model.users.UsersResponse
import com.example.cleanarchtemplate.data.repository.UserRepository
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
class ProfileViewModelTest {

  @Before
  fun setUp() {
    Dispatchers.setMain(UnconfinedTestDispatcher())
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `loadProfile success sets success state with profile`() = runTest {
    val profile = UserProfile(id = 5, firstName = "Jane", lastName = "Doe")
    val viewModel = ProfileViewModel(FakeUserRepository(profile = profile))

    assertEquals(ProfileState.Success(profile), viewModel.state.value)
  }

  @Test
  fun `loadProfile error sets error state`() = runTest {
    val viewModel = ProfileViewModel(FakeUserRepository(throwError = true))

    assertEquals(ProfileState.Error("profile failed"), viewModel.state.value)
  }

  @Test
  fun `loadProfile can be reloaded and updates state`() = runTest {
    val repository = FakeUserRepository(profile = UserProfile(id = 5, firstName = "Jane"))
    val viewModel = ProfileViewModel(repository)

    val updated = UserProfile(id = 5, firstName = "Janet")
    repository.profile = updated
    viewModel.loadProfile()

    assertEquals(ProfileState.Success(updated), viewModel.state.value)
  }

  private class FakeUserRepository(
    var profile: UserProfile = UserProfile(id = 5),
    private val throwError: Boolean = false,
  ) : UserRepository {

    override suspend fun getUser(id: Int): UserProfile {
      if (throwError) throw RuntimeException("profile failed")
      assertEquals(5, id)
      return profile
    }

    override suspend fun getUsers(limit: Int, skip: Int, select: String?): UsersResponse = throw NotImplementedError()
    override suspend fun searchUsers(query: String): UsersResponse = throw NotImplementedError()
  }
}
