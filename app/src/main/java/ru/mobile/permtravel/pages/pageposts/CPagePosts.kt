@file:Suppress("DEPRECATION")

package ru.mobile.permtravel.pages.pageposts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.inputmethodservice.Keyboard
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.mobile.permtravel.model.Author
import ru.mobile.permtravel.model.Post
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.mobile.permtravel.pages.pageauthors.CViewModelPageAuthors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter.State.Empty.painter
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobile.permtravel.pages.pageauthors.CPageAuthors
import ru.mobile.permtravel.pages.pageplacedescription.CViewModelPagePlaceDescription
import ru.mobile.permtravel.pages.pageplaces.CViewModelPagePlaces
import ru.mobile.permtravel.repositories.CRepositoryPlaces

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CPagePosts(
    authorId: String,
    navController: NavHostController
)
{
    val postViewModel: CViewModelPagePosts = viewModel()
    val postsFlow = remember(authorId) { postViewModel.getPostsByAuthorId(UUID.fromString(authorId)) }
    val posts by postsFlow.collectAsState(initial = null)
    val authorFlow = remember(authorId) { postViewModel.getAuthorById(authorId = UUID.fromString(authorId)) }
    val author by authorFlow.collectAsState(initial = null)


    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(500.dp),
                shape = RoundedCornerShape(16.dp),
                )
            {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(author?.let { generateAvatar(it.name) })
                                .crossfade(true)
                                .build()
                        ),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .padding(end = 10.dp)

                    )
                    author?.let { Text(it.name) }
                    Spacer(modifier = Modifier.height(16.dp))
                    author?.let {
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    Text(
                        text = buildAnnotatedString {
                          append("IG:")
                          withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
                              append(author?.instagramLink)
                          }
                        },
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable{openLink(context, "https://www.instagram.com/${author?.instagramLink}" )
                            }
                    )

                    Button(onClick = { showDialog = false }) {
                        Text(text = "Закрыть")
                    }
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer))
    {
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,

        )
        {
            IconButton(onClick = { navController.navigate("authors")}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back"
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable{ showDialog = true }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(author?.let { generateAvatar(it.name) })
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .padding(end = 10.dp)
                )
                author?.let {
                    Text(
                        text = it.name,
                        fontSize = 18.sp
                    )
                }
            }
            IconButton(
                onClick = {navController.navigate("createPost/$authorId")}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Создать пост"
                )
            }

        }
    }
    Column(
        modifier = Modifier
            .padding(top=50.dp),
    ) {
        if (posts?.isEmpty() == true) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = "Постов пока нет",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.6f
                        )
                    )
                )
            }
        } else
            LazyColumn {
                items(posts.orEmpty()) {
                        post -> post?.let {
                    PostItem(post,
                        navController)
                }
                }
            }
    }
}

fun generateAvatar(name: String): Bitmap {
    val firstLetter = name.firstOrNull()?.uppercase() ?: "?"
    val backgroundColor = getColorForLetter(firstLetter)


    val size = 200
    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    val paint = Paint().apply {
        color = backgroundColor.toArgb()
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    // Рисуем круг
    canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

    // Настройки текста
    val textPaint = Paint().apply {
        color = androidx.compose.ui.graphics.Color.White.toArgb()
        textSize = size / 2.5f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }

    // Рисуем букву в центре
    val textBounds = Rect()
    textPaint.getTextBounds(firstLetter, 0, firstLetter.length, textBounds)
    val x = size / 2f
    val y = size / 2f - textBounds.exactCenterY()
    canvas.drawText(firstLetter, x, y, textPaint)

    return bitmap
}

private fun getColorForLetter(letter: String): androidx.compose.ui.graphics.Color {
    val colors = listOf(
        Color(0xFFEF5350), Color(0xFFAB47BC), Color(0xFF5C6BC0),
        Color(0xFF42A5F5), Color(0xFF26A69A), Color(0xFF66BB6A),
        Color(0xFFFFCA28), Color(0xFFFF7043)
    )
    val index = letter[0].code % colors.size
    return colors[index]
}

@Composable
fun PostItem(
    post: Post,
    navController: NavHostController
    ) {
    val placeViewModel: CViewModelPagePlaceDescription = viewModel()

    // Запоминаем ID и следим за изменениями через Flow
    val placeFlow = remember(post.placeId) { placeViewModel.getPlaceByIdForPosts(post.placeId) }

    // Преобразуем Flow в State
    val place by placeFlow.collectAsState(initial = null)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(6.dp)
            .border(1.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
            .padding(6.dp)
            .background(MaterialTheme.colorScheme.background.copy(alpha=0.1f))
    ) {

        Text(
            text = post.text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        post.placeId?.let { placeId ->
            place?.let { placeObj ->
                Text(
                    text = placeObj.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .clickable {
                            navController.navigate("placedescription/${placeObj.id}")
                        }
                        .padding(bottom = 4.dp),
                    textDecoration = TextDecoration.Underline
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Text(
                text = formatDate(post.createdAt),
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
            )
        }
    }
}

fun formatDate(timeInMillis: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    return  dateFormat.format(Date(timeInMillis))
}


fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}