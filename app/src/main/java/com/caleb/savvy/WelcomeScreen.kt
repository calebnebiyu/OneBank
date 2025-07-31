package com.caleb.savvy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caleb.savvy.ui.theme.ButtonColor
import com.caleb.savvy.ui.theme.SavvyTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onSignUp: () -> Unit = {}, onLogin: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Savvy",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Guide your Goals. Grow Your Wealth.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic
            )
            Spacer(Modifier.height(60.dp))
            Text(
                text = "Your money. Your goals. Your way.\n" +
                        "Savvy connects your accounts, tracks spending vs. income, and delivers smart AI-driven insights to help you spend better, save faster, and stay on track.\n\n" +
                        "Start your Savings Journey! Choose a funding account, set your goal, and let Savvy guide your progress based on real income activity.\n\n" +
                        "Manage transfers, monitor trends, and build your financial future one move at a time.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onSignUp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                ) {
                    Text("Sign Up",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
                ) {
                    Text("Login", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
    fun WelcomeScreenPreview() {
        SavvyTheme {
            WelcomeScreen()
        }
    }