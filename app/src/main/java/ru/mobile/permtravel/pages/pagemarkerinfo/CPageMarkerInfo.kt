package ru.mobile.permtravel.pages.pagemarkerinfo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.mobile.permtravel.pages.pageplacedescription.CViewModelPagePlaceDescription
import java.util.UUID

@Composable
fun CPageMarkerInfo(id: String, navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: CViewModelPageMarkerInfo = viewModel()

    // Запоминаем ID и следим за изменениями через Flow
    val placeFlow = remember(id) { viewModel.getPlaceById(UUID.fromString(id)) }

    // Преобразуем Flow в State
    val place by placeFlow.collectAsState(initial = null)

    place?.let {
        LazyColumn(modifier = modifier.padding(16.dp)) {
            item {
                Text(
                    text = "Наименование места: ${it.name}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = String.format("Координаты места: %.6fº, %.6fº", it.latitude, it.longitude),
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp
                )

                Text(text = it.description, fontSize = 16.sp)
            }
        }
    } ?: Text("Место не найдено", modifier = modifier.padding(16.dp))
}