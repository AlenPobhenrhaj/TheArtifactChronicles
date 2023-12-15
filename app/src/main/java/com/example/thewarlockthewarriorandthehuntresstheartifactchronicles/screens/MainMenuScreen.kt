package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens


import android.media.MediaPlayer
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.util.AnimatedButton

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
fun AnimatedBackground() {
    // Implement your custom background animation here
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.menu_background_2),
        contentDescription = "Animated Background"
    )
}


