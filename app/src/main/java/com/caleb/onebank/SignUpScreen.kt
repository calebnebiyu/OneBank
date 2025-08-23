package com.caleb.onebank

import android.util.Patterns
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
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.graphics.Color
import com.caleb.onebank.ui.components.StandardTextField // Ensure this import is present

@Composable
fun SignupScreen(onSignUp: () -> Unit, onNavigateToLogin: () -> Unit) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var firstnameError by remember { mutableStateOf(false) }
    var lastnameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "OneBank",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = "Let's get started! \n Create your account.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(40.dp))

            StandardTextField(
                value = firstname,
                onValueChange = {
                    firstname = it
                    //if (firstnameError) firstnameError = false
                },
                label = { Text("First Name") },
                isError = firstnameError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField(
                value = lastname,
                onValueChange = {
                    lastname = it
                    //if (lastnameError) lastnameError = false
                },
                label = { Text("Last Name") },
                isError = lastnameError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField(
                value = email,
                onValueChange = {
                    email = it
                    //if (emailError) emailError = false
                },
                label = { Text("Email") },
                isError = emailError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField(
                value = password,
                onValueChange = {
                    password = it
                    //if (passwordError) passwordError = false
                    //if (confirmPasswordError && it == confirmPassword) confirmPasswordError = false
                },
                label = { Text("Password") },
                isError = passwordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            StandardTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    //if (confirmPasswordError) confirmPasswordError = false
                },
                label = { Text("Confirm Password") },
                isError = confirmPasswordError,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp)) // Reduced from 42dp to account for less padding in TextField
            Button(
                onClick = {
                    firstnameError = false
                    lastnameError = false
                    emailError = false
                    passwordError = false
                    confirmPasswordError = false
                    var isValid = true
                    if (firstname.isBlank()) {
                        firstnameError = true
                        isValid = false
                    }
                    if (lastname.isBlank()) {
                        lastnameError = true
                        isValid = false
                    }
                    if (email.isBlank()) {
                        emailError = true
                        isValid = false
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        emailError = true
                        isValid = false
                    }
                    if (password.isBlank()) {
                        passwordError = true
                        isValid = false
                    }
                    if (confirmPassword.isBlank()) {
                        confirmPasswordError = true
                        isValid = false
                    } else if (password != confirmPassword) {
                        confirmPasswordError = true
                        isValid = false
                    }
                    if (isValid) {
                        onSignUp()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    "Continue",
                    color = MaterialTheme.colorScheme.onBackground, 
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, 
                    contentColor   = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Login",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    name = "Signup Screen - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Signup Screen - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SignupScreenPreview() {
    OneBankTheme {
        SignupScreen(onSignUp = {}, onNavigateToLogin = {})
    }
}
