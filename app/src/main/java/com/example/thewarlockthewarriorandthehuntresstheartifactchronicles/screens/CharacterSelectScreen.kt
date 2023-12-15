package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens

import android.media.MediaPlayer
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.data.Character
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.util.AnimatedButton
import kotlinx.coroutines.delay

/*
*  Border assets from ChatGPT4, https://www.pinterest.com/pin/448811919106888168/ and https://game-icons.net/
*  */

@Composable
fun CharacterSelectScreen(onStartClicked: () -> Unit) {
    val context = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.menu_theme_2) // Replace with your audio file
    }

    DisposableEffect(Unit) {
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    val characters = listOf(
        Character("Bard The Barbarian", "The publisher of The Rodent's Gazette...", R.drawable.barbarian_1),
        Character("Scribblehopper", "I was Sir Geronimo of Stilton's first guide...", R.drawable.necromancer_1),
        Character("Little Princess Buzzy", "I am the niece of the queen of the bees...", R.drawable.princess_1)
    )

    var currentCharacterIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        DecorativeBorders()

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 52.dp) // Adjust this padding to place the button below the border
        ) {
            Text(
                "The Band of Time Seekers",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 180.dp) // Adjust this value to push the content below the top border
                .align(Alignment.TopCenter)
        ) {

            Spacer(modifier = Modifier.height(16.dp)) // Adjust as needed

            val currentCharacter = characters[currentCharacterIndex]
            AnimatedCharacterCard(
                characterName = currentCharacter.name,
                description = currentCharacter.description,
                characterDrawable = currentCharacter.drawable
            )

            Spacer(modifier = Modifier.height(120.dp)) // Adjust as needed

            Row(modifier = Modifier.padding(20.dp)) {
                Button(onClick = { if (currentCharacterIndex > 0) currentCharacterIndex-- }) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { if (currentCharacterIndex < characters.size - 1) currentCharacterIndex++ }) {
                    Text("Next")
                }
            }
        }

        // Start Adventure button placed within the Box, but outside the Column
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp) // Adjust this padding to place the button below the border
        ) {
            AnimatedButton("Start Adventure", onStartClicked)
        }
    }
}

@Composable
fun AnimatedCharacterCard(characterName: String, description: String, characterDrawable: Int) {
    AnimatedVisibility(
        visible = true, enter = fadeIn() + expandVertically(), exit = fadeOut()
    ) {
        CharacterCard(characterName, description, characterDrawable)
    }
}

@Composable
fun CharacterCard(characterName: String, description: String, characterDrawable: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = characterDrawable),
            contentDescription = "$characterName image",
            modifier = Modifier
                .size(100.dp)
                .fadeInAnimation()
        )
        TypewriterText(
            text = characterName, fontWeight = FontWeight.Bold, color = Color(0xFFF1E3DD)
        )
        TypewriterText(text = description, fontSize = 12.sp, color = Color(0xFFD0C8B0))
    }
}

@Composable
fun TypewriterText(
    text: String, fontWeight: FontWeight? = null, color: Color, fontSize: TextUnit = 16.sp
) {
    var displayText by remember { mutableStateOf(AnnotatedString("")) }
    val textLength = text.length

    LaunchedEffect(key1 = text) {
        displayText = AnnotatedString("")
        repeat(textLength) { index ->
            delay(50) // Delay for typewriter effect speed
            displayText = AnnotatedString(text.substring(0, index + 1))
        }
    }

    Text(displayText, fontWeight = fontWeight, color = color, fontSize = fontSize)
}

fun Modifier.fadeInAnimation(): Modifier = composed {
    val fadeInAnim = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true) {
        fadeInAnim.animateTo(1f, animationSpec = tween(durationMillis = 1000))
    }
    this.graphicsLayer { alpha = fadeInAnim.value }
}


@Composable
fun DecorativeBorders() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Full Border Image
        Image(
            painter = painterResource(R.drawable.gold_frame_border_1), // The name of your border image file
            contentDescription = "Decorative Border", modifier = Modifier.fillMaxSize()
        )

        // Content of the screen goes here...
        // Make sure the content is placed in a way that it's visible within the border frame.
    }
}

@Composable
fun ThemedButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = Modifier.padding(8.dp)
        // Add button theming here
    ) {
        Text(text)
    }
}
