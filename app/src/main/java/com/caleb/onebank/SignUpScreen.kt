package com.caleb.onebank

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import com.caleb.onebank.ui.theme.OneBankTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.text.font.FontStyle // Commented out as FontStyle.Italic is removed


@Composable
fun SignupScreen(onSignUp: () -> Unit, onNavigateToLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // Use background color from theme
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp), // Consistent padding
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "OneBank",
                style = MaterialTheme.typography.headlineLarge, // Larger title
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary // Use primary color from theme
            )
            Spacer(modifier = Modifier.height(65.dp)) // Increased spacing
            Text(
                text = "Let's get started. \n Create your account.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium, // Consistent typography
                color = MaterialTheme.colorScheme.primary // Use primary color
            )
            Spacer(modifier = Modifier.height(32.dp)) // Increased spacing

            // Email TextField
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp), // Rounded corners
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next // Next action for email
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp), // Consistent padding
                colors = TextFieldDefaults.colors( // Remove underline
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(16.dp)) // Consistent spacing

            // Confirm Password TextField
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                singleLine = true,
                shape = RoundedCornerShape(24.dp), // Rounded corners
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done // Done action for confirm password
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp), // Consistent padding
                colors = TextFieldDefaults.colors( // Remove underline
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(20.dp)) // Increased spacing before button
            Button(
                onClick = { /* TODO: Implement signup logic then call onSignUp */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp), // Standard button height
                shape = RoundedCornerShape(24.dp), // Rounded corners for button
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, // Button color from theme
                    contentColor = MaterialTheme.colorScheme.onPrimary // Text color on button from theme
                )
            ) {
                Text(
                    "Sign Up",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(65.dp)) // Spacing before "OR" or alternative action

            Text(
                text = "Already have an account?", // Changed text
                style = MaterialTheme.typography.bodyMedium,
                // fontStyle = FontStyle.Italic, // Removed
                color = MaterialTheme.colorScheme.primary
            )
            // Spacer(modifier = Modifier.height(15.dp)) // This Spacer is REMOVED
            Button(
                onClick = onNavigateToLogin, // Changed onClick
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors( // Changed colors for secondary style
                    containerColor = Color.Transparent,
                    contentColor   = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Login",
                    // color = MaterialTheme.colorScheme.onBackground, // Removed color to use Button's contentColor
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    name = "Signup Screen - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO, // Light mode
    showBackground = true
)
@Preview(
    name = "Signup Screen - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES, // Dark mode
    showBackground = true
)
@Composable
fun SignupScreenPreview() {
    OneBankTheme { // Ensure your theme is applied
        SignupScreen(onSignUp = {}, onNavigateToLogin = {})
    }
}
