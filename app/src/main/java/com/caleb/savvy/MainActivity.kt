package com.caleb.savvy

import com.caleb.savvy.ui.screens.WelcomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.caleb.savvy.ui.theme.SavvyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SavvyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    WelcomeScreen(modifier = Modifier.padding(padding))
                }
            }
        }
    }
}