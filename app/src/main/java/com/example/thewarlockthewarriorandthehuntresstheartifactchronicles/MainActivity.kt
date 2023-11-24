package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.Screens.MainMenuScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameApp()
        }
    }
}

@Composable
fun GameApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainMenu") {
        composable("mainMenu") {
            MainMenuScreen(
                onStartClicked = { /* Define what happens when start is clicked */ },
                onOptionsClicked = { /* Define what happens when options is clicked */ }
            )
        }

        // Further navigation routes can be added here later
    }
}