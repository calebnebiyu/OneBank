package com.caleb.onebank

import android.content.res.Configuration // Added for uiMode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caleb.onebank.ui.theme.OneBankTheme

@Composable
fun SignUpSettingsScreen(onNavigateNext: () -> Unit) { // Added a placeholder navigation lambda
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // It's better to apply background to Surface if it's the root
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top, // Aligning to the top for title
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp)) // Space from the top

            Text(
                text = "OneBank",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(45.dp)) // Space between title and subtitle

            Text(
                text = "A few more details... \n then you're good to go!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.weight(1f)) // Pushes content below to the bottom if any

            // TODO: Add other UI elements for "a few more details" here

            // Example Button to navigate away (you'll define its action later)
            // Button(
            //     onClick = onNavigateNext,
            //     modifier = Modifier
            //         .fillMaxWidth()
            //         .padding(vertical = 16.dp)
            //         .height(48.dp)
            // ) {
            //     Text("Next")
            // }
            // Spacer(modifier = Modifier.height(24.dp)) // Space from bottom
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
        SignUpSettingsScreen(onNavigateNext = {})
    }
}

