package ru.mobile.permtravel.pages.pageplacedescription

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.mobile.permtravel.pages.pageplaces.CViewModelPagePlaces
import java.util.UUID

@Composable
fun CPagePlaceDescription(id : UUID, modifier: Modifier = Modifier) {
    val viewModel : CViewModelPagePlaces = viewModel()
    Text(viewModel.getPlaceDescriptionById(id).toString())
}