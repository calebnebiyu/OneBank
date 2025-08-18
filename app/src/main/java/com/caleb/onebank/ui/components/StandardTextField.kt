package com.caleb.onebank.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.defaultMinSize // Added for clarity, though often covered by other layout imports
import androidx.compose.foundation.layout.fillMaxWidth
// import androidx.compose.foundation.layout.height // This will be replaced by defaultMinSize logic
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text // Keep this if used in previews or examples, remove if not
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Default values
val DefaultTextFieldHeight: Dp = 56.dp // This will be the minHeight
val DefaultTextFieldShape: Shape = RoundedCornerShape(24.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    height: Dp = DefaultTextFieldHeight, // This parameter now feeds defaultMinSize
    shape: Shape = DefaultTextFieldShape,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        // Optional: define container colors for consistency if needed
        // focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        // unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        // disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        // errorContainerColor = MaterialTheme.colorScheme.surfaceVariant,
    )
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = height) // Changed from .height(height)
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = if (isError) MaterialTheme.colorScheme.error else Color.Transparent
                ),
                shape = shape // Apply the shape to the border as well
            ),
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape, // Apply the shape to the TextField itself
        colors = colors
    )
}
