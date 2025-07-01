package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterText(modifier: Modifier) {
    Text(modifier = modifier, text = buildAnnotatedString {
        append("Hesabınız yok mu?")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline
            )
        ) {
            append("Kayıt Ol")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterText() {
    RegisterText(modifier = Modifier)
}