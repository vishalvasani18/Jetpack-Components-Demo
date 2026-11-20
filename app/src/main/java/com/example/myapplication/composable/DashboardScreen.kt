package com.example.myapplication.composable

import HomeScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.myapplication.R
import com.example.myapplication.model.DrawerScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    // Current route
    val currentRoute by navController
        .currentBackStackEntryAsState()
        .let { entry ->
            androidx.compose.runtime.derivedStateOf {
                entry.value?.destination?.route
            }
        }
    // Close drawer lambda
    val closeDrawer: () -> Unit = { scope.launch { drawerState.close() } }

    // Determine toolbar title dynamically
    val currentScreen = when (currentRoute) {
        DrawerScreen.Home.route -> R.string.title_home
        DrawerScreen.Settings.route -> R.string.title_settings
        DrawerScreen.About.route -> R.string.title_about
        DrawerScreen.Info.route -> R.string.title_info
        DrawerScreen.Posts.route -> R.string.title_posts
        DrawerScreen.Comments.route -> R.string.title_comments
        DrawerScreen.Albums.route -> R.string.title_albums
        DrawerScreen.Photos.route -> R.string.title_photos
        DrawerScreen.Todos.route -> R.string.title_todo
        DrawerScreen.Users.route -> R.string.title_users
        DrawerScreen.Language.route -> R.string.title_language_setting

        else -> R.string.app_name
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentRoute != DrawerScreen.Info.route,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(280.dp)) {
                DrawerContent(

                    navController = navController,
                    currentRoute = currentRoute,
                    closeDrawer = closeDrawer
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = currentScreen)) },
                    navigationIcon = {
                        if (currentRoute == DrawerScreen.Info.route || currentRoute == DrawerScreen.Posts.route || currentRoute == DrawerScreen.Comments.route || currentRoute == DrawerScreen.Albums.route || currentRoute == DrawerScreen.Photos.route || currentRoute == DrawerScreen.Users.route || currentRoute == DrawerScreen.Language.route) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        } else {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    },

                    // Actions: show Info icon only on non-Info screens
                    actions = {
                        if (currentRoute != DrawerScreen.Info.route) {
                            IconButton(onClick = { navController.navigate(DrawerScreen.Info.route) }) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info"
                                )
                            }
                        }
                    }
                )
            }
        ) { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = DrawerScreen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(DrawerScreen.Home.route) {
                    HomeScreen(
                        onItemClick ={ route ->
                            navController.navigate(route)
                        }
                    )
                }

                composable(DrawerScreen.Posts.route) {
                    PostListScreen()
                }

                composable(DrawerScreen.Comments.route) {
                    CommentsListScreen()
                }

                composable(DrawerScreen.Albums.route) {
                    AlbumListScreen()
                }

                composable(DrawerScreen.Photos.route) {
                    PhotosListScreen()
                }

                composable(DrawerScreen.Todos.route) {
                    TodoListScreen()
                }

                composable(DrawerScreen.Users.route) {
                    UserListScreen()
                }

                // IMPORTANT: Settings must be here
                composable(DrawerScreen.Settings.route) {
                    SettingsScreen(
                        onLanguageClick = {
                            navController.navigate(DrawerScreen.Language.route)
                        }
                    )
                }

                // Language
                composable(DrawerScreen.Language.route) {
                    LanguageScreen(
                        onLanguageSaved = {
                            navController.navigate(DrawerScreen.Settings.route) {
                                popUpTo(DrawerScreen.Settings.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable(DrawerScreen.About.route) { AboutUsScreen() }
                composable(DrawerScreen.Info.route) { InfoScreen() }
            }
        }
    }
}