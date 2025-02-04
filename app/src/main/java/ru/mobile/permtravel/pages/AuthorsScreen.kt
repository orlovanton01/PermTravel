package ru.mobile.permtravel.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mobile.permtravel.R
import ru.mobile.permtravel.model.Author

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview
fun AuthorsScreen() {
    val authors = listOf( //TODO: Позже загружать из БД
        Author(1,"Иван Иванов", R.drawable.ic_launcher_foreground),
        Author(2,"Петр Петров", R.drawable.ic_launcher_foreground),
        Author(3,"Николай Николаев", R.drawable.ic_launcher_foreground),
        Author(4,"Сергей Сергеев", R.drawable.ic_launcher_foreground),
        Author(5,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(6,"Иван Иванов", R.drawable.ic_launcher_foreground),
        Author(7,"Петр Петров", R.drawable.ic_launcher_foreground),
        Author(8,"Николай Николаев", R.drawable.ic_launcher_foreground),
        Author(9,"Сергей Сергеев", R.drawable.ic_launcher_foreground),
        Author(10,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(11,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(12,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(13,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(14,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(15,"Алексей Алексеев", R.drawable.ic_launcher_foreground),
        Author(16,"Алексей Алексеев", R.drawable.ic_launcher_foreground)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Авторы")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(authors.size) { index ->
                AuthorItem(authors[index])

            }
        }
    }
}

@Composable
fun AuthorItem(author: Author) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = author.imageRes),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .padding(end = 10.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = author.name,
            fontSize = 18.sp
        )
    }
    Divider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    )

}