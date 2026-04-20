package com.example.myapplication.model

sealed class DrawerScreen(val route: String, val title: String) {
    object Home : DrawerScreen("home", "Home")
    object Settings : DrawerScreen("settings", "Settings")
    object About : DrawerScreen("about", "About")
    object Info : DrawerScreen("info", "Info")

}