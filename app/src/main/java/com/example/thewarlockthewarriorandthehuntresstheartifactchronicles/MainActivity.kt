package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.screens.CharacterIntroScreen
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

    NavHost(
        navController = navController,
        startDestination = "mainMenu"
    ) {
        composable("mainMenu") {
            MainMenuScreen(
                onStartClicked = {
                    navController.navigate("characterSelect")
                },
                onOptionsClicked = { }
            )
        }

        // 1) characterSelect now emits the drawable Int
        composable("characterSelect") {
            CharacterSelectScreen { drawableRes ->
                // navigate into gameScreen/<that drawable>
                navController.navigate("gameScreen/$drawableRes")
            }
        }

        // 2) gameScreen route now carries an Int argument called drawableRes
        composable(
            route        = "gameScreen/{drawableRes}",
            arguments   = listOf(
                navArgument("drawableRes") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            // pull it out of the bundle
            val drawable = backStackEntry.arguments?.getInt("drawableRes")
                ?: R.drawable.barbarian_1  // fallback if you like

            // hand it into your intro screen
            CharacterIntroScreen(
                drawable = drawable,
                onContinue = {

                }
            )
        }
    }
}