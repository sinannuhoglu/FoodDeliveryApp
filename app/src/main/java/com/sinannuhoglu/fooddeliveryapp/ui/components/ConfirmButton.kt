package com.sinannuhoglu.fooddeliveryapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFE347C))
    ) {
        Text("Siparişi Tamamla", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConfirmButton() {
    ConfirmButton(onClick = {})
}
