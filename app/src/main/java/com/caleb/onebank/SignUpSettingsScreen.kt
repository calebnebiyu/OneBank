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
import java.util.TimeZone // Needed for timezone list
import java.util.Currency // Needed for currency list

@OptIn(ExperimentalMaterial3Api::class) // Keep OptIn if StandardTextField uses Experimental APIs
@Composable
fun SignUpSettingsScreen(onNavigateContinue: () -> Unit) {
    var country by remember { mutableStateOf("") }
    var countryError by remember { mutableStateOf(false) }
    val allCountries = remember {
        Locale.getISOCountries().map { countryCode ->
            Locale("", countryCode).displayCountry
        }.sorted()
    }
    var countrySuggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var showCountryDropdown by remember { mutableStateOf(false) }

    var timezone by remember { mutableStateOf("") }
    var timezoneError by remember { mutableStateOf(false) }
    val allTimezones = remember { TimeZone.getAvailableIDs().sorted() }
    var timezoneSuggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var showTimezoneDropdown by remember { mutableStateOf(false) }

    var currency by remember { mutableStateOf("") }
    var currencyError by remember { mutableStateOf(false) }
    val allCurrencies = remember { Currency.getAvailableCurrencies().map { it.currencyCode }.sorted() }
    var currencySuggestions by remember { mutableStateOf<List<String>>(emptyList()) }
    var showCurrencyDropdown by remember { mutableStateOf(false) }


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
                    .padding(start = 16.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                StandardTextField(
                    value = country,
                    onValueChange = { newValue ->
                        country = newValue
                        countrySuggestions = if (newValue.isBlank()) {
                            emptyList()
                        } else {
                            allCountries.filter {
                                it.startsWith(newValue, ignoreCase = true)
                            }
                        }
                        showCountryDropdown = countrySuggestions.isNotEmpty()
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
                                if (country.isNotBlank() && countrySuggestions.isNotEmpty()) {
                                    showCountryDropdown = true
                                }
                            } else {
                                // showCountryDropdown = false // Consider delaying this
                            }
                        }
                    )
                DropdownMenu(
                    expanded = showCountryDropdown && countrySuggestions.isNotEmpty(),
                    onDismissRequest = { showCountryDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    countrySuggestions.take(5).forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                country = suggestion
                                showCountryDropdown = false
                                countrySuggestions = emptyList()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Timezone",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                StandardTextField(
                    value = timezone,
                    onValueChange = { newValue ->
                        timezone = newValue
                        timezoneSuggestions = if (newValue.isBlank()) {
                            emptyList()
                        } else {
                            allTimezones.filter {
                                it.contains(newValue, ignoreCase = true) // Contains for broader search
                            }
                        }
                        showTimezoneDropdown = timezoneSuggestions.isNotEmpty()
                        if (timezoneError) timezoneError = false
                    },
                    label = { Text("Timezone") },
                    isError = timezoneError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                if (timezone.isNotBlank() && timezoneSuggestions.isNotEmpty()) {
                                    showTimezoneDropdown = true
                                }
                            } else {
                                // showTimezoneDropdown = false // Consider delaying this
                            }
                        }
                    )
                DropdownMenu(
                    expanded = showTimezoneDropdown && timezoneSuggestions.isNotEmpty(),
                    onDismissRequest = { showTimezoneDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    timezoneSuggestions.take(5).forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                timezone = suggestion
                                showTimezoneDropdown = false
                                timezoneSuggestions = emptyList()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Local Currency",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(5.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                StandardTextField(
                    value = currency,
                    onValueChange = { newValue ->
                        currency = newValue
                        currencySuggestions = if (newValue.isBlank()) {
                            emptyList()
                        } else {
                            allCurrencies.filter {
                                it.startsWith(newValue, ignoreCase = true)
                            }
                        }
                        showCurrencyDropdown = currencySuggestions.isNotEmpty()
                        if (currencyError) currencyError = false
                    },
                    label = { Text("Currency") },
                    isError = currencyError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done // Changed to Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                if (currency.isNotBlank() && currencySuggestions.isNotEmpty()) {
                                    showCurrencyDropdown = true
                                }
                            } else {
                                // showCurrencyDropdown = false // Consider delaying this
                            }
                        }
                    )
                DropdownMenu(
                    expanded = showCurrencyDropdown && currencySuggestions.isNotEmpty(),
                    onDismissRequest = { showCurrencyDropdown = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    currencySuggestions.take(5).forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                currency = suggestion
                                showCurrencyDropdown = false
                                currencySuggestions = emptyList()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { /* TODO: Implement validation and navigation */ },
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
                    "Finish",
                    color = MaterialTheme.colorScheme.onPrimary,
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
