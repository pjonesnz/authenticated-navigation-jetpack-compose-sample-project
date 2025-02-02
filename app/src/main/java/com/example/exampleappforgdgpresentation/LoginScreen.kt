package com.example.exampleappforgdgpresentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exampleappforgdgpresentation.ui.theme.ExampleAppForGDGPresentationTheme

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginScreenViewModel = hiltViewModel(),
) {
    val loginSuccess by viewModel.loginSuccess.collectAsStateWithLifecycle()

    LoginScreen(
        modifier = modifier,
        onLoginClick = viewModel::onLoginClick
    )

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            onLoginSuccess()
        }
    }
}

@Composable
private fun LoginScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFFFAA36)
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .padding(paddingValues = contentPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            var showLoading by remember { mutableStateOf(false) }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                Spacer(Modifier.height(32.dp))
                if (showLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(
                        onClick = {
                            onLoginClick()
                            showLoading = true
                        },
                        modifier = Modifier.width(128.dp)
                    ) {
                        Text(
                            text = "Log in",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    ExampleAppForGDGPresentationTheme {
        LoginScreen(onLoginClick = {})
    }
}