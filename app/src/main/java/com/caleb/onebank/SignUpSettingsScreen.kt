package com.caleb.onebank

import android.content.res.Configuration // Added for uiMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caleb.onebank.ui.components.StandardTextField
import com.caleb.onebank.ui.theme.OneBankTheme
import java.util.Locale // Needed for country list

@OptIn(ExperimentalMaterial3Api::class) // Keep OptIn if StandardTextField uses Experimental APIs
@Composable
fun SignUpSettingsScreen(onNavigateContinue: () -> Unit) {
    var country by remember { mutableStateOf("") }

    var countryError by remember { mutableStateOf(false) }

    // Get all country names
    val allCountries = remember {
        Locale.getISOCountries().map { countryCode ->
            Locale("", countryCode).displayCountry
        }.sorted()
    }
    var countrySuggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var showCountryDropdown by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "OneBank",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(45.dp))

            Text(
                text = "Almost done! \n Fill out the following.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Country of Residence Section with Autocomplete
            Text(
                text = "Country of Residence",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp), // You may adjust or remove this padding as needed
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier.fillMaxWidth()) { // Box for TextField and DropdownMenu
                StandardTextField(
                    value = country,
                    onValueChange = { newValue ->
                        country = newValue
                        if (newValue.isBlank()) {
                            countrySuggestions = emptyList()
                            showCountryDropdown = false
                        } else {
                            countrySuggestions = allCountries.filter {
                                it.startsWith(newValue, ignoreCase = true)
                            }
                            // Show dropdown if there are suggestions and text field has text
                            showCountryDropdown = countrySuggestions.isNotEmpty()
                        }
                        if (countryError) countryError = false
                    },
                    label = { Text("Country") },
                    isError = countryError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                // If field gains focus and has text, show suggestions if any
                                if (country.isNotBlank() && countrySuggestions.isNotEmpty()) {
                                    showCountryDropdown = true
                                }
                            } else {
                                // This is a common place to add a small delay if dropdown closes too quickly
                                // For now, onDismissRequest will handle clicks outside.
                                // showCountryDropdown = false 
                            }
                        }
                )

                DropdownMenu(
                    expanded = showCountryDropdown && countrySuggestions.isNotEmpty(),
                    onDismissRequest = { showCountryDropdown = false },
                    modifier = Modifier.fillMaxWidth() // Makes dropdown same width as text field
                ) {
                    countrySuggestions.take(5).forEach { suggestion -> // Limiting to 5 suggestions
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                country = suggestion
                                showCountryDropdown = false
                                countrySuggestions = emptyList() // Clear suggestions
                            }
                        )
                    }
                }
            }
            // End of Autocomplete Country Section

            // Placeholder for other fields like email, password, etc.
            // Remember to remove the duplicated "Country of Residence" Text and StandardTextField
            // that were previously below the first one if they are no longer needed.

            // Example:
            // Spacer(modifier = Modifier.height(20.dp))
            // Text(text = "Email", ...)
            // StandardTextField(value = email, onValueChange = { email = it }, ...)
            // ... and so on for password and confirmPassword
            Button(
                onClick = onNavigateContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp), // Standard button height
                shape = RoundedCornerShape(24.dp), // Standard button shape
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    "Finish",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    name = "SignUp Settings Screen - Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "SignUp Settings Screen - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SignUpSettingsScreenPreview() {
    OneBankTheme {
        SignUpSettingsScreen(onNavigateContinue = {})
    }
}
