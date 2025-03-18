package ru.mobile.permtravel.pages.pageauthors

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.mobile.permtravel.model.Author

@SuppressLint("SuspiciousIndentation")
@Composable
fun CPageAuthors(navController: NavController, modifier : Modifier = Modifier) {
    val viewModel : CViewModelPageAuthors = viewModel()
    val authors by viewModel.authors.collectAsState()
    if (authors.isEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ){
            Text(
                text = "Загрузка...",
                fontSize = 20.sp,
                modifier = modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
    else
    {
        LazyColumn  {
            items(
                authors,
                key = {author -> author.id}
            ) { author ->
                AuthorItem(
                    author,
                    modifier,
                    onClick = {author1 ->
                        navController.navigate("posts/${author1.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun AuthorItem(
    author: Author,
    modifier: Modifier,
    onClick : (Author) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                run {onClick(author)}
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(author.avatar)
                    .crossfade(true)
                    .build()
            ),
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
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
}