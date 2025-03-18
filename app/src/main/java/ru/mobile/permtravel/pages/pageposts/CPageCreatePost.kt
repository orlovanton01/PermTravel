package ru.mobile.permtravel.pages.pageposts

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import java.util.UUID

@Composable
fun CPageCreatePost(
    authorId: String,
    navController: NavHostController
)
{
    val postViewModel: CViewModelPagePosts = viewModel()
    var text by remember {mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Создать пост", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text("Введите текст поста") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            TextButton(onClick = {navController.navigate("posts/$authorId")}) {
                Text("Отмена")
            }
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        val newPost = Post(
                            id = UUID.randomUUID(),
                            authorId = UUID.fromString(authorId),
                            text = text,
                            createdAt = System.currentTimeMillis()
                        )
                        postViewModel.insertPost(newPost)
                        navController.navigate("posts/$authorId")
                    }
                }
            ) { Text("Опубликовать") }
        }
    }
}