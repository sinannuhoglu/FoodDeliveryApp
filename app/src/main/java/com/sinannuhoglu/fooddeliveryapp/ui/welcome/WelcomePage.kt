package com.sinannuhoglu.fooddeliveryapp.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sinannuhoglu.fooddeliveryapp.ui.components.FilledWhiteButton
import com.sinannuhoglu.fooddeliveryapp.ui.components.LargeSpace
import com.sinannuhoglu.fooddeliveryapp.ui.components.MediumSpace
import com.sinannuhoglu.fooddeliveryapp.ui.components.OutlinedButtonItem
import com.sinannuhoglu.fooddeliveryapp.ui.components.TitleSection

@Composable
fun WelcomePage(
    onSignInClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFE347C), Color(0xFFF674A0))
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TitleSection(
                title = "Başlayalım",
                subtitle = "Kayıt ol veya giriş yaparak başla"
            )
            LargeSpace()
            OutlinedButtonItem(text = "GİRİŞ YAP", onClick = onSignInClick)
            MediumSpace()
            FilledWhiteButton(text = "KAYIT OL", onClick = onSignUpClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomePage() {
    WelcomePage()
}
