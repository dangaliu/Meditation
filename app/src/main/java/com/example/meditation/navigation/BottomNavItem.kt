package com.example.meditation.navigation

import com.example.meditation.R

sealed class BottomNavItem(val title: String, val res: Int, val route: String) {
    object Home: BottomNavItem("Home", R.drawable.ic_home_bottom, "home")
    object Music: BottomNavItem("Music", R.drawable.ic_music, "music")
    object Profile: BottomNavItem("Profile", R.drawable.ic_profile, "profile")
}
