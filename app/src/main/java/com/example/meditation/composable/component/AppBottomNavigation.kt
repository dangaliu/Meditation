package com.example.meditation.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.meditation.navigation.BottomNavItem
import com.example.meditation.navigation.NavConstants
import com.example.meditation.ui.theme.BackgroundColor

@Composable
fun AppBottomNavigation(
    navController: NavHostController
) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.Music,
        BottomNavItem.Profile
    )

    BottomNavigation(
        backgroundColor = BackgroundColor,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentScreen = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            val selected = currentScreen == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
//                        navController.graph.startDestinationRoute?.let { route ->
//                            popUpTo(route) {
//                                saveState = true
//                            }
//
//                            restoreState = true
//                            launchSingleTop = true
//                        }
                        popUpTo(NavConstants.main) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.res),
                        contentDescription = item.title,
                        modifier = if (selected) Modifier.scale(1.2f) else Modifier,
                        tint = if (selected) Color.White else Color.White.copy(0.2f)
                    )
                }
            )
        }
    }
}