package com.caleb.onebank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
// import androidx.compose.runtime.Composable // Removed as it's not directly used here now
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.caleb.onebank.WelcomeScreen // Changed
import com.caleb.onebank.SignupScreen
import com.caleb.onebank.LoginScreen
import com.caleb.onebank.ui.theme.OneBankTheme // Changed

object AppRoutes {
    const val WELCOME = "welcome"
    const val SIGNUP = "signup"
    const val LOGIN = "login"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneBankTheme { // Changed
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
                                    // TODO: Implement actual sign-up logic
                                    // For now, maybe navigate back or to a different screen
                                    // Example: navController.popBackStack()
                                    // Example: navController.navigate("some_other_screen_after_signup")
                                },
                                onNavigateToLogin = { navController.navigate(AppRoutes.LOGIN) }
                            )
                        }
                        composable(AppRoutes.LOGIN) {
                            LoginScreen(
                                onLoginClicked = {
                                    // TODO: Implement actual login logic
                                    // For now, you might want to navigate somewhere else or pop back
                                    // Example: navController.popBackStack() // To go back to WelcomeScreen
                                    // Example: navController.navigate("home_screen") { popUpTo(AppRoutes.WELCOME) }
                                },
                                onNavigateToSignUp = { navController.navigate(AppRoutes.SIGNUP) }
                            )
                        }
                    }
                }
            }
        }
    }
}