package com.example.thewarlockthewarriorandthehuntresstheartifactchronicles.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun StatusBar(
    lives: Int,
    maxLives: Int,
    mana: Int,
    @DrawableRes centerDrawable: Int,
    maxMana: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Health section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Health: $lives")
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(maxLives) { idx ->
                    val filled = idx < lives
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = if (filled) Color.Black else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    if (idx != maxLives - 1) Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }

        // Center drawable
        Image(
            painter = painterResource(centerDrawable),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )

        // Mana section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Mana: $mana")
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(maxMana) { idx ->
                    val filled = idx < mana
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = if (filled) Color.Black else Color.LightGray,
                                shape = CircleShape
                            )
                    )
                    if (idx != maxMana - 1) Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}
