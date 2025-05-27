package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens

import android.media.MediaPlayer
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.ui.theme.StatusBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterIntroScreen(
    @DrawableRes drawable: Int,
    onContinue: () -> Unit
) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.menu_theme_2)
    }

    DisposableEffect(Unit) {
        mediaPlayer.start()
        mediaPlayer.isLooping = true
        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    Scaffold(
        bottomBar = {
            StatusBar(
                lives = 3,
                maxLives = 5,
                mana = 2,
                maxMana = 5,
                centerDrawable = drawable,
            )
        }
    ) { padding ->
        // your intro content goes here
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // … draw character art, text, Continue button, etc.
        }
    }
}