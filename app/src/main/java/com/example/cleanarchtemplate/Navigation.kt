package com.example.cleanarchtemplate

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.cleanarchtemplate.ui.auth.LoginScreen
import com.example.cleanarchtemplate.ui.home.HomeScreen

@Composable
fun Navigation() {
  // Simple auth check state can go here later, start with Login
  val backStack = rememberNavBackStack(NavigationKeys.Route.LOGIN)

  NavDisplay(
    backStack = backStack,
    onBack = { backStack.removeLastOrNull() },
    entryProvider =
      entryProvider {
        entry<NavigationKeys.Route.LOGIN> {
          LoginScreen(
            onLoginSuccess = { 
               backStack.clear()
               backStack.add(NavigationKeys.Route.HOME)
            },
            modifier = Modifier.safeDrawingPadding().padding(16.dp)
          )
        }
        entry<NavigationKeys.Route.HOME> {
          HomeScreen(
            onProductClick = { productId ->
                backStack.add(NavigationKeys.Route.PRODUCT_DETAIL(productId))
            }
          )
        }
        entry<NavigationKeys.Route.PRODUCT_DETAIL> {
          val productId = it.id
          com.example.cleanarchtemplate.ui.detail.ProductDetailScreen(
            productId = productId,
            onBack = { backStack.removeLastOrNull() }
          )
        }
      },
  )
}
