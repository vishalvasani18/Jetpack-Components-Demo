import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.DrawerScreen

@Composable
fun HomeScreen(onItemClick: (String) -> Unit) {

    // Sample data: 6 items
    val itemsList = listOf(
        DrawerScreen.Posts.route to stringResource(R.string.title_posts),
        DrawerScreen.Comments.route to stringResource(R.string.title_comments),
        DrawerScreen.Albums.route to stringResource(R.string.title_albums),
        DrawerScreen.Photos.route to stringResource(R.string.title_photos),
        DrawerScreen.Todos.route to stringResource(R.string.title_todo),
        DrawerScreen.Users.route to stringResource(R.string.title_users)
    )


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        content = {
            items(itemsList) {(route,title)->
                GridItem(
                    title = title,
                    onClick = { onItemClick(route)}
                )
            }
        }
    )
}

@Composable
fun GridItem(title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = title)
        }
    }
}
