package com.example.cleanarchtemplate.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.cleanarchtemplate.ui.cart.CartScreen
import com.example.cleanarchtemplate.ui.main.ProductListScreen
import com.example.cleanarchtemplate.ui.profile.ProfileScreen

@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(0) }
    
    val items = listOf("Products", "Cart", "Profile")

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Text(item.first().toString()) },
                        label = { Text(item) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> ProductListScreen(onProductClick = onProductClick)
                1 -> CartScreen()
                2 -> ProfileScreen()
            }
        }
    }
}
