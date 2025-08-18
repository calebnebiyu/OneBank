package com.caleb.onebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caleb.onebank.WelcomeScreen
import com.caleb.onebank.SignupScreen
import com.caleb.onebank.LoginScreen
import com.caleb.onebank.SignUpSettingsScreen // Added import
import com.caleb.onebank.navigation.AppRoutes // Added import
import com.caleb.onebank.ui.theme.OneBankTheme

// Removed the old AppRoutes object that was here

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneBankTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppRoutes.WELCOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(AppRoutes.WELCOME) {
                            WelcomeScreen(
                                onSignUp = { navController.navigate(AppRoutes.SIGNUP) },
                                onLogin = { navController.navigate(AppRoutes.LOGIN) }
                            )
                        }
                        composable(AppRoutes.SIGNUP) {
                            SignupScreen(
                                onSignUp = {
                                    // This will be changed in SignupScreen.kt to navigate to SIGNUP_SETTINGS
                                    navController.navigate(AppRoutes.SIGNUP_SETTINGS) // Updated for clarity
                                },
                                onNavigateToLogin = { navController.navigate(AppRoutes.LOGIN) }
                            )
                        }
                        composable(AppRoutes.LOGIN) {
                            LoginScreen(
                                onLogin = { // Corrected: was onLoginClicked
                                    // TODO: Implement actual login logic
                                },
                                onNavigateToSignup = { navController.navigate(AppRoutes.SIGNUP) } // Corrected: was onNavigateToSignUp
                            )
                        }
                        composable(AppRoutes.SIGNUP_SETTINGS) { // New route added
                            SignUpSettingsScreen(
                                onNavigateNext = {
                                    // TODO: Define actual navigation for the "Next" button
                                    // For now, let's navigate back to Login as an example
                                    navController.navigate(AppRoutes.LOGIN) {
                                        popUpTo(AppRoutes.WELCOME) // Clear back stack up to Welcome
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}