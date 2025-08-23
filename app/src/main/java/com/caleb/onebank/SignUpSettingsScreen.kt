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
import java.util.Currency // Added for currency information
import java.util.TimeZone // Added for timezone information

// Helper function to generate flag emoji from country code
fun getFlagEmoji(countryCode: String): String {
    if (countryCode.length != 2) return "" // Not a valid ISO country code
    // Check if the country code consists of letters only (more robust)
    if (!countryCode.all { it.isLetter() }) return "❓"

    val firstLetter = Character.codePointAt(countryCode.uppercase(Locale.ROOT), 0) - 'A'.code + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCode.uppercase(Locale.ROOT), 1) - 'A'.code + 0x1F1E6

    if (!Character.isValidCodePoint(firstLetter) || !Character.isValidCodePoint(secondLetter)) {
        return "❓" // Fallback for invalid codes
    }
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

@OptIn(ExperimentalMaterial3Api::class) // Keep OptIn if StandardTextField uses Experimental APIs
@Composable
fun SignUpSettingsScreen(onNavigateContinue: () -> Unit) {
    var countryInput by remember { mutableStateOf("") } // Renamed for clarity, was 'country'
    var countryError by remember { mutableStateOf(false) }

    // Store pairs of (displayName, countryCode)
    val allCountries = remember {
        Locale.getISOCountries().mapNotNull { code ->
            val locale = Locale("", code)
            // Filter out locales that don't have a displayable country name or are not 2 letters
            if (locale.displayCountry.isNotBlank() && code.length == 2 && code.all { it.isLetter() }) {
                locale.displayCountry to code // Pair of (Display Name, Country Code)
            } else {
                null
            }
        }.sortedBy { it.first } // Sort by display name
    }

    var countrySuggestions by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }
    var showCountryDropdown by remember { mutableStateOf(false) }

    // State variables for displaying country details
    var countryFlag by remember { mutableStateOf("") }
    var countryCurrency by remember { mutableStateOf("") }
    var countryTimezone by remember { mutableStateOf("") }


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
            Spacer(modifier = Modifier.height(5.dp))

            Box(modifier = Modifier.fillMaxWidth()) { // Box for TextField and DropdownMenu
                StandardTextField(
                    value = countryInput,
                    onValueChange = { newValue ->
                        countryInput = newValue
                        if (newValue.isBlank()) {
                            countrySuggestions = emptyList()
                            showCountryDropdown = false
                            // Clear details when input is blank
                            countryFlag = ""
                            countryCurrency = ""
                            countryTimezone = ""
                        } else {
                            countrySuggestions = allCountries.filter {
                                it.first.startsWith(newValue, ignoreCase = true) // Filter by display name
                            }
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
                                if (countryInput.isNotBlank() && countrySuggestions.isNotEmpty()) {
                                    showCountryDropdown = true
                                }
                            } else {
                                // Dropdown dismiss handled by onDismissRequest or item click
                            }
                        }
                )

                DropdownMenu(
                    expanded = showCountryDropdown && countrySuggestions.isNotEmpty(),
                    onDismissRequest = { showCountryDropdown = false },
                    modifier = Modifier.fillMaxWidth() // Makes dropdown same width as text field
                ) {
                    countrySuggestions.take(5).forEach { (name, code) -> // Destructure Pair
                        DropdownMenuItem(
                            text = { Text(name) }, // Display name
                            onClick = {
                                countryInput = name // Set text field to the selected country name
                                val selectedCountryCode = code // Use the country code for lookups
                                showCountryDropdown = false
                                countrySuggestions = emptyList() // Clear suggestions

                                try {
                                    val locale = Locale("", selectedCountryCode)
                                    countryFlag = getFlagEmoji(selectedCountryCode)

                                    val currencyInstance = Currency.getInstance(locale)
                                    countryCurrency = "${currencyInstance.currencyCode} (${currencyInstance.symbol})"

                                    val timezones = java.util.TimeZone.getAvailableIDs(selectedCountryCode)
                                    countryTimezone = if (timezones.isNotEmpty()) timezones[0] else "N/A"

                                } catch (e: IllegalArgumentException) {
                                    countryFlag = getFlagEmoji(selectedCountryCode)
                                    countryCurrency = "N/A (Invalid Code)"
                                    countryTimezone = "N/A"
                                } catch (e: Exception) {
                                    countryFlag = "❓"
                                    countryCurrency = "N/A"
                                    countryTimezone = "N/A"
                                }
                            }
                        )
                    }
                }
            }
            // End of Autocomplete Country Section

            // Display country details if available
            if (countryFlag.isNotBlank()) {
                Text(
                    text = "Flag: $countryFlag",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 8.dp) // Added top padding
                )
            }
            if (countryCurrency.isNotBlank()) {
                Text(
                    text = "Currency: $countryCurrency",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 4.dp)
                )
            }
            if (countryTimezone.isNotBlank()) {
                Text(
                    text = "Timezone: $countryTimezone",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { /* DO NOTHING FOR NOW */ }, // Changed from onNavigateContinue
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
                    color = MaterialTheme.colorScheme.onPrimary, // Changed from onBackground for better contrast
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp)) // Added Spacer at the end for better scrollability
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

