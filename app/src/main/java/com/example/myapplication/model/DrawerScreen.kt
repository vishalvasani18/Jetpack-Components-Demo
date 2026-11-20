package com.example.myapplication.model

sealed class DrawerScreen(val route: String, val title: String) {
    object Home : DrawerScreen("home", "Home")
    object Settings : DrawerScreen("settings", "Settings")
    object About : DrawerScreen("about", "About")
    object Info : DrawerScreen("info", "Info")
    object Posts : DrawerScreen("Posts", "Post List")
    object Comments : DrawerScreen("Comments", "Comment List")
    object Albums: DrawerScreen("Albums", "Album List")
    object Photos: DrawerScreen("Photos", "Photo List")
    object Todos: DrawerScreen("Todos", "Todo List")
    object Users: DrawerScreen("Users", "User List")
    object Language : DrawerScreen("language", "Language")
}