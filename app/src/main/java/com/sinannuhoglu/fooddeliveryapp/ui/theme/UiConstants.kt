package com.sinannuhoglu.fooddeliveryapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object UiConstants {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color.White, Color(0xFFD3D3D3))
    )

    val mainButtonColor = Color(0xFFFE347C)
    val mainGrayTextColor = Color.Gray

    val defaultPadding = 16.dp
    val smallPadding = 8.dp
    val spacerHeight = 16.dp

    val headerFontSize = 10.sp

    val cornerRadius = 16.dp
    val mapHeight = 220.dp

    val snackbarPadding = 32.dp
}
