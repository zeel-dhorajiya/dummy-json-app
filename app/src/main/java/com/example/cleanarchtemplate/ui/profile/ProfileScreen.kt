package com.example.cleanarchtemplate.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cleanarchtemplate.ui.components.ErrorScreen
import com.example.cleanarchtemplate.ui.components.LoadingScreen
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("Profile") }
        )

        when (val uiState = state) {
            is ProfileState.Loading -> {
                LoadingScreen(modifier = Modifier.weight(1f))
            }
            is ProfileState.Error -> {
                ErrorScreen(
                    message = uiState.message,
                    onRetry = { viewModel.loadProfile() },
                    modifier = Modifier.weight(1f)
                )
            }
            is ProfileState.Success -> {
                val profile = uiState.profile
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar placeholder
                    Surface(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.size(120.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "${profile.firstName?.firstOrNull() ?: ""}${profile.lastName?.firstOrNull() ?: ""}",
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "${profile.firstName ?: ""} ${profile.lastName ?: ""}",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = profile.email ?: "No Email",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    
                    // Stats row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ProfileStat(label = "Age", value = profile.age?.toString() ?: "-")
                        ProfileStat(label = "Gender", value = profile.gender?.capitalize() ?: "-")
                        ProfileStat(label = "Role", value = profile.role?.capitalize() ?: "-")
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileStat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
