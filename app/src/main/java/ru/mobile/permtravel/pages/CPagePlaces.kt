package ru.mobile.permtravel.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mobile.permtravel.R

@Composable
fun CPagePlaces(modifier : Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize()
            .background(Color.White, shape = RoundedCornerShape(32.dp)) // Белый фон и закруглённые углы
            .fillMaxSize()
    ){
        Column(
            modifier = modifier
                .padding(16.dp)
        )
        {
            Text(
                text = "Усьвинские столбы",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = modifier
                    .padding(bottom = 5.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.usvinsky_pillars),
                contentDescription = "Усьвинские столбы",
                contentScale = ContentScale.Crop
            )
        }
    }
}