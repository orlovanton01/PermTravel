package ru.mobile.permtravel.pages.pagemarkergallery

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import java.util.UUID

@Composable
fun CPageMarkerGallery(id: String, modifier: Modifier = Modifier) {
    val viewModel: CViewModelPageMarkerGallery = viewModel()
    // Запоминаем ID и следим за изменениями через Flow
    val placeFlow = remember(id) { viewModel.getPlaceById(UUID.fromString(id)) }
    // Преобразуем Flow в State
    val place by placeFlow.collectAsState(initial = null)

    place?.let {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            item {
                // Наименование страницы
                Text(
                    text = "Галерея: ${it.name}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )

                // Изображение
                val imageModifier = modifier
                    .height(400.dp)
                AsyncImage(
                    model = it.photoPath,  // Загружаем из локального пути
                    contentDescription = it.name,
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier
                )
            }
        }
    } ?: Text("Не удалось загрузить галерею для достопримечательности", modifier = modifier.padding(16.dp))
}

