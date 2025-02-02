package com.example.exampleappforgdgpresentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.exampleappforgdgpresentation.ui.theme.ExampleAppForGDGPresentationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleAppForGDGPresentationTheme {
//                UsingComposable()
                UsingAuthenticatedComposable()
            }
        }
    }

    @Composable
    fun UsingComposable() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = LoginRoute,
            modifier = Modifier.fillMaxSize(),
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    onOpenContentClick = { navController.navigate(ContentRoute) }
                )
            }

            composable<LoginRoute> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(HomeRoute) {
                            popUpTo(LoginRoute) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable<ContentRoute>(
                deepLinks = listOf(
                    navDeepLink(
                        deepLinkBuilder = {
                            uriPattern = "https://example.com/content"
                        }
                    )
                )
            ) {
                ContentScreen(
                    onBackPress = navController::navigateUp
                )
            }
        }
    }

    @Composable
    fun UsingAuthenticatedComposable() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.fillMaxSize(),
        ) {
            authenticatedComposable<HomeRoute>(navController = navController) {
                HomeScreen(
                    onOpenContentClick = { navController.navigate(ContentRoute) }
                )
            }

            composable<LoginRoute> {
                LoginScreen(
                    onLoginSuccess = {
                        navController.popBackStack(
                            route = LoginRoute,
                            inclusive = true
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            authenticatedComposable<ContentRoute>(
                navController = navController,
                deepLinks = listOf(
                    navDeepLink(
                        deepLinkBuilder = {
                            uriPattern = "https://example.com/content"
                        }
                    )
                )
            ) {
                ContentScreen(
                    onBackPress = navController::navigateUp
                )
            }
        }
    }
}