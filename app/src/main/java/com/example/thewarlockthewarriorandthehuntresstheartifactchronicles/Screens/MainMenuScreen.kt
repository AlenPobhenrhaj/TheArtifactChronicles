package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.Screens


import android.media.MediaPlayer
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R

/*
*  Music assets by https://freemusicpack.giannicanetti.com/
*  */

@Composable
fun MainMenuScreen(onStartClicked: () -> Unit, onOptionsClicked: () -> Unit) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(
            context,
            R.raw.menu_theme_2
        )
    } // Replace with your audio file name

    DisposableEffect(Unit) {
        mediaPlayer.start()
        mediaPlayer.isLooping = true // If you want the audio to loop

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        isVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.menu_background_2),
            contentDescription = "Background"
        )

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(700)) + slideInVertically()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedButton("Start", onStartClicked)
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedButton("Options", onOptionsClicked)
            }
        }
    }
}

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

@Composable
fun AnimatedBackground() {
    // Implement your custom background animation here
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.menu_background_2),
        contentDescription = "Animated Background"
    )
}


