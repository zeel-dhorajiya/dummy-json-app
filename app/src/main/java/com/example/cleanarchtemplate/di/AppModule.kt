package com.example.cleanarchtemplate.di

import android.content.Context
import com.example.cleanarchtemplate.data.api.ApiService
import com.example.cleanarchtemplate.data.api.NetworkModule
import com.example.cleanarchtemplate.data.local.TokenManager
import com.example.cleanarchtemplate.data.repository.AuthRepository
import com.example.cleanarchtemplate.data.repository.CartRepository
import com.example.cleanarchtemplate.data.repository.DefaultAuthRepository
import com.example.cleanarchtemplate.data.repository.DefaultCartRepository
import com.example.cleanarchtemplate.data.repository.DefaultProductRepository
import com.example.cleanarchtemplate.data.repository.DefaultUserRepository
import com.example.cleanarchtemplate.data.repository.ProductRepository
import com.example.cleanarchtemplate.data.repository.UserRepository
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.core.module.dsl.viewModel

val networkModule = module {
  single<Json> { NetworkModule.provideJson() }
  single<HttpLoggingInterceptor> { NetworkModule.provideLoggingInterceptor() }
  single<OkHttpClient> { NetworkModule.provideOkHttpClient(get()) }
  single<Retrofit> { NetworkModule.provideRetrofit(get(), get()) }
  single<ApiService> { NetworkModule.provideApiService(get()) }
}

val localModule = module {
  single<TokenManager> { TokenManager(get<Context>()) }
}

val repositoryModule = module {
  single<AuthRepository> { DefaultAuthRepository(get()) }
  single<ProductRepository> { DefaultProductRepository(get()) }
  single<CartRepository> { DefaultCartRepository(get()) }
  single<UserRepository> { DefaultUserRepository(get()) }
}
val viewModelModule = module {
    viewModel { com.example.cleanarchtemplate.ui.auth.LoginViewModel(get(), get()) }
}

val mainViewModelModule = module {
    viewModel { com.example.cleanarchtemplate.ui.main.ProductListViewModel(get()) }
}

val cartViewModelModule = module {
    viewModel { com.example.cleanarchtemplate.ui.cart.CartViewModel(get(), get()) }
}

val profileViewModelModule = module {
    viewModel { com.example.cleanarchtemplate.ui.profile.ProfileViewModel(get()) }
}

val detailViewModelModule = module {
    viewModel { com.example.cleanarchtemplate.ui.detail.ProductDetailViewModel(get()) }
}
