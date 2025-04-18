package com.fabiofpaulo.mmogames.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Redeem
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Games : BottomBarScreen(
        route = "GAMES",
        title = "Games",
        icon = Icons.Default.Games
    )

    object Giveaways : BottomBarScreen(
        route = "GIVEAWAYS",
        title = "Giveaways",
        icon = Icons.Default.Redeem
    )


}