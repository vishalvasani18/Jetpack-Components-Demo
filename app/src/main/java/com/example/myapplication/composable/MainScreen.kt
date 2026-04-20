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
import com.example.myapplication.composable.AboutUsScreen
import com.example.myapplication.composable.DrawerContent
import com.example.myapplication.composable.InfoScreen
import com.example.myapplication.composable.PostListScreen
import com.example.myapplication.composable.SettingsScreen
import com.example.myapplication.model.DrawerScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    // Current route
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route

    // Close drawer lambda
    val closeDrawer: () -> Unit = { scope.launch { drawerState.close() } }

    // Determine toolbar title dynamically
    val titleRes = when (currentRoute) {
        DrawerScreen.Home.route -> R.string.title_home
        DrawerScreen.Settings.route -> R.string.title_settings
        DrawerScreen.About.route -> R.string.title_about
        DrawerScreen.Info.route -> R.string.title_info
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
                    title = { Text(stringResource(id = titleRes)) },
                    navigationIcon = {
                        if (currentRoute == DrawerScreen.Info.route) {
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
                        onItemClick = { item ->
                            if (item == "Posts") {
                                navController.navigate("Posts")
                            }
                        }
                    )
                }

                composable("Posts") {
                    PostListScreen()
                }
                composable(DrawerScreen.Settings.route) { SettingsScreen() }
                composable(DrawerScreen.About.route) { AboutUsScreen() }
                composable(DrawerScreen.Info.route) { InfoScreen() }
            }
        }
    }
}