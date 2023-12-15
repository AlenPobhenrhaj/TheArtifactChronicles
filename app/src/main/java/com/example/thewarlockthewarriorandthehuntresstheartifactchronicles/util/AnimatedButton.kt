package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedButton(text: String, onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        modifier = Modifier.scale(scale.value)
    ) {
        Text(
            text,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            //fontFamily = fontResource(R.font.diablo_font) // Replace with your custom font
        )
    }
}