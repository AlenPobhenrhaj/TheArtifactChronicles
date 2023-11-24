package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.R

@Composable
fun CharacterSelectScreen(onStartClicked: () -> Unit, onOptionsClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Background Image
        Image(modifier = Modifier.fillMaxSize(), painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Background")

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Start Button
            Button(onClick = onStartClicked) {
                Text("Start")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Options Button
            Button(onClick = onOptionsClicked) {
                Text("Options")
            }
        }
    }
}