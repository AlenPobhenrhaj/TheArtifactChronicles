package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens.CharacterSelectScreen
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens.MainMenuScreen

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
                onStartClicked = {
                    navController.navigate("characterSelect")
                },
                onOptionsClicked = {
                    // Actions for options click
                }
            )
        }

        composable("characterSelect") {
            CharacterSelectScreen(
                onStartClicked = {
                    navController.navigate("gameScreen")
                }
            )
        }

        composable("gameScreen") {
            GameScreen()  // Ensure you have a composable function called GameScreen defined
        }

        // Further navigation routes can be added here
    }
}

// Define the GameScreen composable function here
@Composable
fun GameScreen() {
    // Implement your game screen UI here
}