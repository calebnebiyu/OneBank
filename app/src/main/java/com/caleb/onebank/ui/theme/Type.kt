package com.caleb.onebank.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import com.caleb.onebank.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular,    weight = FontWeight.Normal),
    Font(R.font.montserrat_italic,     weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.montserrat_bold,       weight = FontWeight.Bold),
)

val AppTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = Montserrat,
        fontSize    = 64.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize   = 20.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Montserrat,
        fontSize   = 15.sp
    )
)