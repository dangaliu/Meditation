package com.example.meditation.composable.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import com.example.meditation.ui.theme.ClearRippleTheme

@Composable
fun AppBottomNavigation(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Music,
        BottomNavItem.Profile
    )

    CompositionLocalProvider(
        LocalRippleTheme provides ClearRippleTheme
    ) {
        BottomNavigation(
            backgroundColor = BackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            elevation = 0.dp
        ) {

            val navBackStackEntry = navController.currentBackStackEntryAsState().value
            val currentScreen = navBackStackEntry?.destination?.route

            items.forEachIndexed { index, item ->
                val selected = currentScreen == item.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(item.route) {
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
}