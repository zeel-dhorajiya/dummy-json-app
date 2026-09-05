package com.example.cleanarchtemplate.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cleanarchtemplate.data.model.cart.CartProduct
import com.example.cleanarchtemplate.ui.components.ErrorScreen
import com.example.cleanarchtemplate.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("My Cart") }
        )

        when (val uiState = state) {
            is CartState.Loading -> {
                LoadingScreen(modifier = Modifier.weight(1f))
            }
            is CartState.Error -> {
                ErrorScreen(
                    message = uiState.message,
                    onRetry = { viewModel.loadUserCart() },
                    modifier = Modifier.weight(1f)
                )
            }
            is CartState.Success -> {
                val cart = uiState.cart
                if (cart == null || cart.products.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Your cart is empty", style = MaterialTheme.typography.titleMedium)
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        items(cart.products) { product ->
                            CartItemRow(product = product)
                        }
                    }

                    // Checkout Bottom Bar
                    Surface(
                        shadowElevation = 8.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Total", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    text = "$${cart.total}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Button(onClick = { /* Handle Checkout */ }) {
                                Text("Checkout")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(product: CartProduct) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.title ?: "Unknown",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Qty: ${product.quantity ?: 1}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "$${product.total ?: 0.0}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
