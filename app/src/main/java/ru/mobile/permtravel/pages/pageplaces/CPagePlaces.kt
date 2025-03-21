package ru.mobile.permtravel.pages.pageplaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.mobile.permtravel.model.CPlace
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@SuppressLint("SuspiciousIndentation")
@Composable
fun CPagePlaces(navController: NavController, modifier : Modifier = Modifier) {
    val viewModel : CViewModelPagePlaces = viewModel()
    val places by viewModel.places.collectAsState()
    if (places.isEmpty()) {
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
    } else {
        LazyColumn (
            state = rememberLazyListState(),
            modifier = modifier
                .padding(top = 32.dp)
        ) {
            items(
                places,
                key = { place -> place.id }
            ) {
                    place ->
                Place(
                    place,
                    modifier,
                    onClick = {place1 ->
                        navController.navigate("placedescription/${place1.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun Place(
    place: CPlace,
    modifier: Modifier,
    onClick : (CPlace) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(32.dp)) // Белый фон и закруглённые углы
    ){
        Column(
            modifier = modifier
                .padding(16.dp)
                .clickable {
                    run { onClick(place) }
                }
        )
        {
            Text(
                text = place.name,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth()
            )

            val imageModifier = modifier
                .height(400.dp)
            AsyncImage(
                model = place.photoPath,  // Загружаем из локального пути
                contentDescription = place.name,
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
        }
    }
}