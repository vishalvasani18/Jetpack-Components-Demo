package com.example.myapplication.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.model.DrawerScreen

@Composable
fun DrawerContent(
    navController: NavController,
    currentRoute: String?,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Welcome",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        DrawerItem(
            title = "Home",
            selected = currentRoute == DrawerScreen.Home.route,
            {
                navController.navigate(DrawerScreen.Home.route) {
                    launchSingleTop = true
                }
                closeDrawer()
            }
        )

        DrawerItem(
            title = "Settings",
            selected = currentRoute == DrawerScreen.Settings.route
        ) {
            navController.navigate(DrawerScreen.Settings.route) {
                launchSingleTop = true
            }
            closeDrawer()
        }

        DrawerItem(
            title = "About",
            selected = currentRoute == DrawerScreen.About.route
        ) {
            navController.navigate(DrawerScreen.About.route) {
                launchSingleTop = true
            }
            closeDrawer()
        }
    }
}

@Composable
fun DrawerItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = { Text(title) },
        selected = selected,
        onClick = onClick,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}